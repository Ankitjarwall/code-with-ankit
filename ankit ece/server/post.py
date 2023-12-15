import network
import time
import urequests
import BlynkLib
from time import sleep
import machine
from hcsr04 import HCSR04
from machine import UART, Pin
from servo import Servo

led=Pin(27,Pin.OUT)
my_servo = Servo(machine.Pin(14))
uart = UART(2, baudrate=115200)
sensor = HCSR04(trigger_pin=12, echo_pin=13, echo_timeout_us=1000000)
timeout = 0
data = {
        "Distance": "",
        }

wifi = network.WLAN(network.STA_IF)
wifi.active(True)

networks = wifi.scan()

print(networks)

wifi.connect('FTTH','d2101@lpu')

if not wifi.isconnected():
    print('connecting..')
    while (not wifi.isconnected() and timeout < 5):
        print(5 - timeout)
        timeout = timeout + 1
        time.sleep(1)

def servo():
    if int(value[0]) is not 0:
        print('Gate Open...')
        my_servo.write_angle(90)
    else:
        print('Gate Close...')
        my_servo.write_angle(0)

def v1_write_handler(value):
    if int(value[0]) is not 0:
        print('Blub On...')
        led.value(1)
    else:
        print('Blub Off...')
        led.value(0)


while True:    
    distance = sensor.distance_cm()
    
    if(wifi.isconnected()):
        print('Connected')
        if(distance<=20):
            data["Distance"]=str(distance)
            req = urequests.get('http://192.168.1.36:3000/api/data')
            print(req.status_code)
            print(req.text)
            print("Object distance: ",data)
        sleep(2)
    else:
        print('Time Out')
        print('Not Connected')
        sleep(2)
