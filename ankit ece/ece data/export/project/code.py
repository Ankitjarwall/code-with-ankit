from pad4pi import rpi_gpio
import sqlite3
import sys
import I2C_LCD_driver
from time import *
import datetime
import hashlib
from pyfingerprint import PyFingerprint
import calendar

###connecting to the lcd driver
mylcd = I2C_LCD_driver.lcd()

pressed_keys = ''
pin = "12345"
fun = ''
yer = ''
branch = ''
last = ''
roll_id = ''
r = ''

def finger():
    ## Search for a finger
    ##
    ## Tries to initialize the sensor
    try:
        f = PyFingerprint('/dev/ttyUSB0', 9600, 0xFFFFFFFF, 0x00000000)

        if (f.verifyPassword() is False):
            raise ValueError('The given fingerprint sensor password is wrong!')
            startChoice()
    except Exception as e:
        print('The fingerprint sensor could not be initialized!')
        print('Exception message: ' + str(e))
        startChoice()

    ## Gets some sensor information
    print('Currently used templates: ' + str(f.getTemplateCount()))

    ## Tries to search the finger and calculate hash
    try:
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Place your', 1)
        mylcd.lcd_display_string('right thumb', 2)

        ## Wait for the finger to be read
        while (f.readImage() is False):
            pass

        ## Converts the read image to characteristics and stores it in charbuffer 1
        f.convertImage(0x01)

        ## Searches for a template
        result = f.searchTemplate()

        positionNumber = result[0]
        accuracyScore = result[1]

        if (positionNumber == -1):
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Do not match', 1, 2)
            sleep(2)
            mylcd.lcd_clear()
            startChoice()
        else:
            mylcd.lcd_clear()
            today_name = datetime.date.today()
            if (calendar.day_name[today_name.weekday()] != 'Sunday'):
                ## Generating TIME VALUES
                now = datetime.datetime.now()
                my_time_string_7 = "07:00:00"
                my_time_string_9 = "09:00:00"
                my_time_string_10 = "10:30:00"
                my_time_string_11 = "10:45:00"
                my_time_string_12 = "12:15:00"
                my_time_string_13 = "13:15:00"
                my_time_string_14 = "16:00:00"
                time_7 = datetime.datetime.strptime(my_time_string_7, "%H:%M:%S")
                time_9 = datetime.datetime.strptime(my_time_string_9, "%H:%M:%S")
                time_10 = datetime.datetime.strptime(my_time_string_10, "%H:%M:%S")
                time_11 = datetime.datetime.strptime(my_time_string_11, "%H:%M:%S")
                time_12 = datetime.datetime.strptime(my_time_string_12, "%H:%M:%S")
                time_13 = datetime.datetime.strptime(my_time_string_13, "%H:%M:%S")
                time_14 = datetime.datetime.strptime(my_time_string_14, "%H:%M:%S")

                # I am supposing that the date must be the same as now
                time_7 = now.replace(hour=time_7.time().hour, minute=time_7.time().minute, second=time_7.time().second, microsecond=0)
                time_9 = now.replace(hour=time_9.time().hour, minute=time_9.time().minute, second=time_9.time().second, microsecond=0)
                time_10 = now.replace(hour=time_10.time().hour, minute=time_10.time().minute, second=time_10.time().second, microsecond=0)
                time_11 = now.replace(hour=time_11.time().hour, minute=time_11.time().minute, second=time_11.time().second, microsecond=0)
                time_12 = now.replace(hour=time_12.time().hour, minute=time_12.time().minute, second=time_12.time().second, microsecond=0)
                time_13 = now.replace(hour=time_13.time().hour, minute=time_13.time().minute, second=time_13.time().second, microsecond=0)
                time_14 = now.replace(hour=time_14.time().hour, minute=time_14.time().minute, second=time_14.time().second, microsecond=0)

                print('Found template at position "#' + str(positionNumber))
                mylcd.lcd_display_string('Please Wait', 1)

                ## Create Hash Value for finger
                ##

                ## Loads the found template to charbuffer 1
                f.loadTemplate(positionNumber, 0x01)

                ## Downloads the characteristics of the template loaded in charbuffer 1
                characteristics = str(f.downloadCharacteristics(0x01)).encode('utf-8')
                val_hash = hashlib.sha256(characteristics).hexdigest()
                ## Hashes characteristics of the template
                print('SHA-2 hash of template: ' + val_hash)

                ## GETTING INFORMATION FROM DATABASE
                conn = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                curs = conn.cursor()
                db_val = curs.execute('SELECT rollnum,hashval FROM finger_store WHERE id in (values(?))', [positionNumber])
                for row in db_val:
                    ext_id = row[0]
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("YOUR ID NUMBER", 1, 2)
                    mylcd.lcd_display_string(ext_id, 2, 4)
                    sleep(2)
                    mylcd.lcd_clear()
                conn.commit()
                conn.close()

                con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                curs2 = con.cursor()
                curs2.execute('SELECT date from attendance where (date, rollnum) in (values(?, ?))', (today_name, ext_id))
                d = curs2.fetchone()
                con.close()

                if d is None:
                    con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                    c = con.cursor()
                    c.execute('INSERT INTO attendance (rollnum,date) values(?, ?)', (ext_id, today_name))
                    con.commit()
                    con.close()
                ## GETTING INFORMATION FROM DATABASE
                ##con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                ##curs2 = con.cursor()
                ##curs2.execute('SELECT statusfirst,statussecond,statusthird,statuslab from attendance where (date, rollnum) in (values(?, ?))', (today_name, ext_id))
                ##con.close()

                if (now <= time_9) and (now > time_7):
                    con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                    c = con.cursor()
                    c.execute('UPDATE attendance SET statusfirst = ? WHERE (rollnum, date) in (values(?, ?))', ('present', ext_id, today_name))
                    con.commit()
                    con.close()
                    mylcd.lcd_display_string("Attendance", 1)
                    mylcd.lcd_display_string("Successful", 2)
                    sleep(2)
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("See You at ", 1, 2)
                    mylcd.lcd_display_string("10:45 AM", 2, 2)
                    sleep(2)
                    mylcd.lcd_clear()

                elif (now >= time_9) and (now <= time_10):
                    mylcd.lcd_display_string("Sorry", 1, 3)
                    mylcd.lcd_dispplay_string("You are late", 2, 2)
                    sleep(2)
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("Come early for", 1, 2)
                    mylcd.lcd_display_string(" the second class", 2)
                    sleep(2)
                    mylcd.lcd_clear()

                elif (now > time_13) and (now <= time_14):
                    con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                    c = con.cursor()
                    c.execute('UPDATE attendance SET statuslab = ? WHERE (rollnum, date) in (values(?, ?))', ('present', ext_id, today_name))
                    con.commit()
                    con.close()
                    mylcd.lcd_display_string('Attendance for', 1)
                    mylcd.lcd_display_string('Lab Success', 2)
                    sleep(2)
                    mylcd.lcd_clear()

                elif (now <= time_11) and (now > time_10):
                    con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                    c = con.cursor()
                    c.execute('UPDATE attendance SET statussecond = ? WHERE (rollnum, date) in (values(?, ?))',
                              ('present', ext_id, datetime.date.today()))
                    con.commit()
                    con.close()
                    mylcd.lcd_display_string("Attendance Success", 1)
                    mylcd.lcd_display_string("See you at 2 PM", 2)
                    sleep(3)
                    mylcd.lcd_clear()

                elif (now > time_11) and (now <= time_12):
                    mylcd.lcd_display_string("Sorry", 1, 3)
                    mylcd.lcd_display_string("You are late", 2)
                    sleep(3)
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("Come early for", 1)
                    mylcd.lcd_display_string("the third class", 2)
                    sleep(3)
                    mylcd.lcd_clear()

                elif (now <= time_12) and (now > time_11):
                    con = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
                    c = con.cursor()
                    c.execute('UPDATE attendance SET statusthird = ? WHERE (rollnum, date) in (values(?, ?))',
                              ('present', ext_id, today_name))
                    con.commit()
                    con.close()
                    mylcd.lcd_display_string("Attendance", 1, 2)
                    mylcd.lcd_display_string("Successful", 2, 2)
                    sleep(2)
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("See You", 1, 3)
                    mylcd.lcd_display_string("At 2:00 PM ", 2, 2)
                    sleep(3)
                    mylcd.lcd_clear()

                elif (now > time_12) and (now < time_13):
                    mylcd.lcd_display_string("Sorry", 1, 3)
                    mylcd.lcd_display_string("You are late", 2)
                    sleep(2)
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("Please be early ", 1)
                    mylcd.lcd_display_string("to the lab", 2)
                    sleep(2)
                    mylcd.lcd_clear()

                elif now > time_14:
                    mylcd.lcd_display_string("Sorry", 1, 3)
                    mylcd.lcd_display_string("You are Late", 2)
                    sleep(2)
                    mylcd.lcd_clear()
                    mylcd.lcd_display_string("Please Come", 1)
                    mylcd.lcd_display_string("Early Tomorrow", 2)
                    sleep(2)
                    mylcd.lcd_clear()
            else:
                mylcd.lcd_clear()
                mylcd.lcd_display_string('Holiday today', 1)
                mylcd.lcd_display_string('** Enjoy **', 2, 2)
                sleep(2)
            startChoice()
    except Exception as e:
        print('Operation failed!')
        print('Exception message: ' + str(e))
        startChoice()

##Enroll
def enroll():
    global roll_id
    print(roll_id)
    r = roll_id
    print("Roll Number is: ", r)
    ## GETTING INFORMATION FROM DATABASE
    conn = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
    curs = conn.cursor()
    db_val = curs.execute("SELECT rollnum FROM finger_store WHERE rollnum = (values(?))", [r])
    coun = len(list(db_val))
    print(coun)

    if coun >= 1:
        mylcd.lcd_clear()
        mylcd.lcd_display_string('ID Number', 1, 2)
        mylcd.lcd_display_string('Already Taken', 2, 2)
        sleep(2)
        startChoice()
        conn.commit()
        conn.close()
    conn.commit()
    conn.close()
    ## Enrolls new finger
    ##
    ## Tries to initialize the sensor
    try:
        f = PyFingerprint('/dev/ttyUSB0', 9600, 0xFFFFFFFF, 0x00000000)

        if (f.verifyPassword() is False):
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Contact Admin', 1)
            sleep(2)
            raise ValueError('The given fingerprint sensor password is wrong!')

    except Exception as e:
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Contact Admin', 1)
        sleep(2)
        print('The fingerprint sensor could not be initialized!')
        print('Exception message: ' + str(e))
        startChoice()

    ## Gets some sensor information
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Currently used', 1)
    mylcd.lcd_display_string('templates: ', 2)
    mylcd.lcd_display_string(str(f.getTemplateCount()), 2, 13)
    sleep(2)
    print('Currently used templates: ' + str(f.getTemplateCount()))

    ## Tries to enroll new finger
    try:
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Place your', 1, 2)
        mylcd.lcd_display_string('right thumb', 2, 2)

        ## Wait that finger is read
        while (f.readImage() is False):
            pass

        ## Converts read image to characteristics and stores it in charbuffer 1
        f.convertImage(0x01)

        ## Checks if finger is already enrolled
        result = f.searchTemplate()
        positionNumber = result[0]

        if (positionNumber >= 0):
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Template already', 1)
            mylcd.lcd_display_string('exists', 2)
            sleep(2)
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Position "#"', 1)
            mylcd.lcd_display_string(str(positionNumber), 1, 10)
            sleep(3)
            print('Template already exists at position "#' + str(positionNumber))
            startChoice()
        else:
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Please Wait', 1)
            time.sleep(2)

            mylcd.lcd_clear()
            mylcd.lcd_display_string('Waiting for same', 1)
            mylcd.lcd_display_string(' finger again', 2)
            print('Waiting for the same finger again...')

            ## Wait that finger is read again
            while (f.readImage() is False):
                pass

            ## Converts read image to characteristics and stores it in charbuffer 2
            f.convertImage(0x02)

            ## Compares the charbuffers
            if (f.compareCharacteristics() == 0):
                mylcd.lcd_clear()
                mylcd.lcd_display_string('Do not match', 1, 2)
                sleep(2)
                raise Exception('Fingers do not match')
            ## Creates a template
            f.createTemplate()

            ## Saves template at a new position number
            positionNumber = f.storeTemplate()

            ## Loads the found template to charbuffer 1
            f.loadTemplate(positionNumber, 0x01)

            ## Downloads the characteristics of the template loaded in charbuffer 1
            characterics = str(f.downloadCharacteristics(0x01)).encode('utf-8')

            ## Hashes characteristics of the template
            cre_hash = hashlib.sha256(characterics).hexdigest()
            conn = sqlite3.connect('/home/pi/Desktop/Biometric-Attendance-System-Mod/attendance/app.db')
            curs = conn.cursor()
            curs.execute('INSERT INTO finger_store(rollnum, hashval, id) VALUES(?, ?, ?)', (r, cre_hash, positionNumber))
            conn.commit()
            conn.close()
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Finger enrolled', 1)
            mylcd.lcd_display_string('Successfully!', 2)
            sleep(4)
            print('New template position "#' + str(positionNumber))
            startChoice()

    except Exception as e:
        print('Operation failed!')
        print('Exception message: ' + str(e))
        startChoice()


## Start for Enrollment Process

def pwd():
    global fun
    fun = fun.replace(fun, 'password')
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Enter Password', 1, 2)
    print('Enter Password')


def passWord(key):
    global pressed_keys
    global pin
    if key == '#':
        print(pressed_keys)
        if pressed_keys == pin:
            clear_keys()
            year()
        else:
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Wrong Password', 1)
            print('Wrong Password')
            sleep(2)
            startChoice()
    else:
        pressed_keys += key


def year():
    global fun
    fun = fun.replace(fun, 'year')
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Last 2 digits', 1)
    mylcd.lcd_display_string('of Joining Year', 2)
    print('Enter last two digits of Joining year')


def yearJoin(key):
    global pressed_keys
    if key == '#':
        if len(pressed_keys) != 2:
            clear_keys()
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Enter Only', 1)
            mylcd.lcd_display_string('2 Digit Number', 2)
            print("Enter only 2 DIGIT Number")
            sleep(2)
            year()
        else:
            yer = pressed_keys
            clear_keys()
            branch()
    else:
        pressed_keys += key


def branch():
    global fun
    fun = fun.replace(fun, 'branch')
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Choose branch', 1, 2)
    mylcd.lcd_display_string('1.CSE  2.ECE', 2, 2)
    print('Choose Branch')
    print('1.CSE  2.ECE')


def chooseBranch(key):
    global pressed_keys
    global Branch
    if key == '#':
        if pressed_keys == '1':
            Branch = 'CS'
            clear_keys()
            las()
        elif pressed_keys == '2':
            Branch = 'EC'
            clear_keys()
            las()
        else:
            clear_keys()
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Choose The', 1, 2)
            mylcd.lcd_display_string('Correct Number', 2)
            print('Choose correct number')
            sleep(2)
            branch()
    else:
        pressed_keys += key


def las():
    global fun
    fun = fun.replace(fun, 'last')
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Enter Last Three', 1)
    mylcd.lcd_display_string('digits of ID', 2)
    print('Enter last three digits of ID')


def lastFour(key):
    global pressed_keys
    global last
    if key == '#':
        if len(pressed_keys) == 3:
            last = pressed_keys
            clear_keys()
            conform()
        else:
            clear_keys()
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Enter exactly', 1)
            mylcd.lcd_display_string('3 numbers', 2)
            print('Enter exactly 3 numbers')
            sleep(2)
            las()
    else:
        pressed_keys += key


def conform():
    global fun
    global roll_id
    fun = fun.replace(fun, 'conform')
    roll_id = fun.join([Branch, yer, last])
    mylcd.lcd_clear()
    mylcd.lcd_display_string('ID is ', 1)
    mylcd.lcd_display_string(roll_id, 2, 3)
    sleep(2)
    print('Your Id is: ', roll_id)
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Press * to', 1)
    mylcd.lcd_display_string('Conform', 2)
    print('Press * to conform')
    sleep(2)


def conformId(key):
    if key == '*':
        clear_keys()
        mylcd.lcd_clear()
        mylcd.lcd_display_string('ID conformation', 1)
        mylcd.lcd_display_string('is Successful', 2)
        sleep(2)
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Please Wait', 1)
        sleep(2)
        enroll()
    else:
        clear_keys()
        fun()
    pass


def clear_keys():
    global pressed_keys
    pressed_keys = ''


## Start for Attendance
def pinCheck():
    global fun
    fun = fun.replace(fun, 'password')
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Enter Password', 1, 2)
    print('Enter Password')


def checkPassword(key):
    global pressed_keys
    global pin
    if key == '#':
        if pressed_keys == pin:
            clear_keys()
            attendMenu()
        else:
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Wrong Password', 1)
            print('Wrong Password')
            sleep(2)
            startChoice()
    else:
        pressed_keys += key


def attendMenu():
    mylcd.lcd_clear()
    mylcd.lcd_display_string('1.Take', 1)
    mylcd.lcd_display_string('Attendance', 2, 2)
    print('Choose an option:')
    print('1. Take Attendance')


def choice(key):
    if key == '1':
        clear_keys()
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Place your', 1)
        mylcd.lcd_display_string('Right thumb', 2)
        finger()
    else:
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Invalid Choice', 1, 2)
        sleep(2)
        startChoice()


def startChoice():
    mylcd.lcd_clear()
    mylcd.lcd_display_string('1. Enrollment', 1)
    mylcd.lcd_display_string('2. Attendance', 2)
    print('Choose an option:')
    print('1. Enrollment')
    print('2. Attendance')


def main(key):
    global pressed_keys
    if key == '1':
        clear_keys()
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Place your right', 1)
        mylcd.lcd_display_string('Thumb', 2)
        finger()
    elif key == '2':
        clear_keys()
        mylcd.lcd_clear()
        mylcd.lcd_display_string('Enter Password', 1, 2)
        print('Enter Password')
    elif key == '*':
        startChoice()


def fingerprint():
    mylcd.lcd_clear()
    mylcd.lcd_display_string('Place your', 1)
    mylcd.lcd_display_string('Right thumb', 2)
    finger()
    pass


##Listening to the keypad
try:
    factory = rpi_gpio.KeypadFactory()

    keypad = factory.create_keypad(keypad=8)

    keypad.registerKeyPressHandler(keyPress)

except KeyboardInterrupt:
    print('Program Ended')
    mylcd.lcd_clear()

while True:
    # Your code here.
    pass
