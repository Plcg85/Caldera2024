import serial, time

arduino = serial.Serial("COM5",9600)
time.sleep(2)
arduino.write(b'b')
arduino.close()