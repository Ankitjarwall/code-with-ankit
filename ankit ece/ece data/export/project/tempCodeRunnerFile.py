import sqlite3
import hashlib
import I2C_LCD_driver
from time import sleep
import datetime
import RPi.GPIO as GPIO
import time
from pyfingerprint import PyFingerprint 

# Initialize LCD
mylcd = I2C_LCD_driver.lcd()

# Initialize the fingerprint sensor, database connection, and other variables

def main():
    while True:
        mylcd.lcd_clear()
        mylcd.lcd_display_string('1. Attendance', 1)
        mylcd.lcd_display_string('2. Registration', 2)

        choice = get_user_choice()
        
        if choice == '1':
            handle_attendance()
        elif choice == '2':
            handle_registration()
        else:
            mylcd.lcd_clear()
            mylcd.lcd_display_string('Invalid choice', 1)
            sleep(1)


def get_user_choice():
    GPIO.setmode(GPIO.BCM)
    GPIO.setwarnings(False)
    
    MATRIX = [
        ["1", "2", "3", "A"],
        ["4", "5", "6", "B"],
        ["7", "8", "9", "C"],
        ["*", "0", "#", "D"]
    ]
    ROW_PINS = [16, 20, 21, 5]  # BCM numbering for row pins
    COL_PINS = [6, 13, 19, 26]  # BCM numbering for column pins
    
    for j in range(4):
        GPIO.setup(COL_PINS[j], GPIO.OUT)
        GPIO.output(COL_PINS[j], 1)

    for i in range(4):
        GPIO.setup(ROW_PINS[i], GPIO.IN, pull_up_down=GPIO.PUD_UP)

    try:
        while True:
            # Scan the keypad for keypress
            for j in range(4):
                GPIO.output(COL_PINS[j], 0)
                for i in range(4):
                    if GPIO.input(ROW_PINS[i]) == 0:
                        return MATRIX[i][j]  # Return the pressed key

                GPIO.output(COL_PINS[j], 1)
    except KeyboardInterrupt:
        pass
    finally:
        GPIO.cleanup()


def handle_attendance():
    try:
        fingerprint_data = capture_fingerprint()
        
        if fingerprint_data:
            user_id = match_fingerprint(fingerprint_data)
            
            if user_id:
                record_attendance(user_id)
                display_message("Attendance recorded")
            else:
                display_message("Fingerprint not recognized")
        else:
            display_message("Fingerprint capture failed")
            
    except Exception as e:
        print("Error:", str(e))
        
    sleep(2)


def capture_fingerprint():
    try:
        # Initialize the fingerprint sensor
        fingerprint_sensor = PyFingerprint('/dev/ttyUSB0', 57600, 0xFFFFFFFF, 0x00000000)
        
        if not fingerprint_sensor.verifyPassword():
            raise Exception("Fingerprint sensor password is incorrect!")
        display_message("Place your finger on the sensor...")
        while True:
            if fingerprint_sensor.readImage():
                fingerprint_sensor.convertImage(0x01)
                fingerprint_data = fingerprint_sensor.downloadCharacteristics(0x01)
                display_message("Fingerprint captured successfully.")
                return fingerprint_data
    except Exception as e:
        display_message("Fingerprint capture failed: ", str(e))
        return None

def display_message(message):
    mylcd.lcd_clear()
    mylcd.lcd_display_string(message, 1)
    sleep(2)

if __name__ == "__main__":
    main()
