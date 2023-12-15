import RPi.GPIO as GPIO
import time
import i2c_LCD_driverfile as LCD
from pyfingerprint.pyfingerprint import PyFingerprint
import sqlite3

# GPIO pin configuration
GPIO.setmode(GPIO.BCM)

# I2C LCD Configuration
LCD_I2C_ADDR = 0x27
LCD_I2C_SDA_PIN = 2  # GPIO pin 2 for SDA
LCD_I2C_SCL_PIN = 3  # GPIO pin 3 for SCL
GPIO.setup(LCD_I2C_SDA_PIN, GPIO.OUT)

# Keypad pins (4x4 matrix)
ROWS = [4, 17, 27, 22]
COLS = [5, 6, 13, 19]

# Initialize LCD
lcd = LCD.Adafruit_CharLCDPlate(address=LCD_I2C_ADDR, busnum=1)

# Initialize Fingerprint Sensor
fingerprint = PyFingerprint('/dev/ttyUSB0', 57600, 0xFFFFFFFF, 0x00000000)

# Initialize the local database
conn = sqlite3.connect('attendance.db')
cursor = conn.cursor()

# Create Database Tables
cursor.execute('''
CREATE TABLE IF NOT EXISTS students (
    reg_no TEXT PRIMARY KEY,
    name TEXT,
    section TEXT
)
''')

cursor.execute('''
CREATE TABLE IF NOT EXISTS attendance (
    reg_no TEXT,
    date TEXT,
    time TEXT
)
''')

# Keypad Setup
for row in ROWS:
    GPIO.setup(row, GPIO.OUT)
    GPIO.output(row, GPIO.HIGH)

for col in COLS:
    GPIO.setup(col, GPIO.OUT)  # Set COLS as OUTPUT
    GPIO.output(col, GPIO.LOW)  # Set COLS to LOW initially

# Define Keypad Mapping
keys = [
    ['1', '2', '3', 'A'],
    ['4', '5', '6', 'B'],
    ['7', '8', '9', 'C'],
    ['*', '0', '#', 'D']
]

# Functions

def get_key():
    key = None
    while key is None:
        for col_num, col_pin in enumerate(COLS):
            GPIO.output(COLS, GPIO.LOW)
            GPIO.output(col_pin, GPIO.HIGH)
            for row_num, row_pin in enumerate(ROWS):
                if GPIO.input(row_pin) == GPIO.HIGH:
                    key = keys[row_num][col_num]
                    while GPIO.input(row_pin) == GPIO.HIGH:
                        pass
            GPIO.output(col_pin, GPIO.LOW)
    return key

def add_student():
    reg_no = input("Enter student registration number: ")
    name = input("Enter student name: ")
    section = input("Enter student section: ")
    
    cursor.execute("INSERT INTO students VALUES (?, ?, ?)", (reg_no, name, section))
    conn.commit()
    
    print("Student added successfully!")

def delete_student():
    reg_no = input("Enter student registration number to delete: ")
    
    cursor.execute("DELETE FROM students WHERE reg_no=?", (reg_no,))
    conn.commit()
    
    print("Student deleted successfully!")

def mark_attendance():
    reg_no = input("Enter student registration number: ")
    
    if fingerprint_verification():
        current_time = time.strftime('%H:%M:%S')
        current_date = time.strftime('%Y-%m-%d')
        
        cursor.execute("INSERT INTO attendance VALUES (?, ?, ?)", (reg_no, current_date, current_time))
        conn.commit()
        
        print("Attendance marked successfully!")

def view_attendance():
    reg_no = input("Enter student registration number to view attendance: ")
    
    cursor.execute("SELECT date, time FROM attendance WHERE reg_no=?", (reg_no,))
    records = cursor.fetchall()
    
    print("Attendance records for student", reg_no)
    for record in records:
        print("Date:", record[0], "Time:", record[1])

def fingerprint_verification():
    lcd.clear()
    lcd.message("Place your finger")
    
    while not fingerprint.readImage():
        pass
    
    fingerprint.convertImage(0x01)
    
    if fingerprint.searchTemplate() >= 0:
        lcd.clear()
        lcd.message("Fingerprint matched")
        return True
    else:
        lcd.clear()
        lcd.message("Fingerprint not matched")
        return False

try:
    while True:
        key = get_key()
        
        if key == 'A':
            add_student()
        elif key == 'B':
            delete_student()
        elif key == 'C':
            mark_attendance()
        elif key == 'D':
            view_attendance()
finally:
    GPIO.cleanup()
