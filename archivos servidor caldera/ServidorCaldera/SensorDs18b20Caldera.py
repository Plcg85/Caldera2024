# Importing Libraries 
import serial 
import time 

temperatura = 0.0

def devuelveTemperatura():
    arduino.write(b'a')
    time.sleep(1.00) 
    data = arduino.readline() 
    data = data[0:6]
    return data
    
def guardarDatos(temperatura):
    fichero = open("C:\\ServidorCaldera\\datos.txt", 'w')
    fichero.write(str(temperatura) + '\n')
    fichero.close()

#Programa principal####################################################
#######################################################################

arduino = serial.Serial(port='COM5', baudrate=9600, timeout=.1) 

time.sleep(2) 

temperatura = float(devuelveTemperatura())

guardarDatos(temperatura)
 
arduino.close()