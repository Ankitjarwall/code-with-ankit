import Adafruit_DHT
import time
sensor = Adafruit_DHT.DHT22
pin = 4
while True:
    humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)

    if humidity is not None and temperature is notNone:
        print(f'Temperature: {temperature:.2f}°C')
        print(f'Humidity: {humidity:.2f}%')
    else:
        print('Failed to retrieve data from the sensor. Check your connections.')

    time.sleep(2)
