import BlynkLib
import time

BLYNK_AUTH = 'YourAuthToken'  # Replace 'YourAuthToken' with your Blynk Auth Token

# Initialize Blynk
blynk = BlynkLib.Blynk(BLYNK_AUTH)

@blynk.VIRTUAL_WRITE(1)  # Change the V1 to the corresponding virtual pin on your Blynk app
def v1_write_handler(value):
    print("Virtual Pin 1 value:", value[0])
    # Add your desired logic here based on the value received from the app
    # For example, controlling an LED
    if value[0] == '1':
        # Code to turn on the LED or perform any action
        print("Turning LED ON")
    else:
        # Code to turn off the LED or perform any other action
        print("Turning LED OFF")

while True:
    blynk.run()
    time.sleep(0.1)