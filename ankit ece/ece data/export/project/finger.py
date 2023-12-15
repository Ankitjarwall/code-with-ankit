from r305 import R305
import sys

device   = sys.argv[1] # the serial port to which the module is connected
baudrate = sys.argv[2] # the default baudrate for this module is 57600

dev = R305(device, baudrate)

def callback(data):
    """
    This function is called when you are instructed to put the finger on the
    module the second time.
    """
    x = raw_input(data)

result = dev.StoreFingerPrint(IgnoreChecksum=True, callback=callback)
print(result)