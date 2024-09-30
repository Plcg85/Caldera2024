# Importing Libraries 
import serial 
import time 

temperatura = 0.0

def devuelveTemperatura():
    arduino.write(b'a')
    time.sleep(0.05) 
    data = arduino.readline() 
    data = data[1:6]
    return data
    

def devuelveHumedad():
    arduino.write(b'b')
    time.sleep(0.05)
    data = arduino.readline()
    data = data[1:6]
    return data
   
def guardarDatos(temperatura,humedad):
    fichero = open("C:\\ServidorCasa\\datos.txt", 'w')
    fichero.write(str(temperatura) + '\n')
    fichero.write(str(humedad) + '\n')
    fichero.close()

    fichero2 = open("C:\\ServidorCasa\\datosaux.txt", 'w')
    fichero2.write(str(temperatura) + '\n')
    fichero2.write(str(humedad) + '\n')
    fichero2.close()
    

#Programa principal####################################################
#######################################################################

arduino = serial.Serial(port='COM9', baudrate=9600, timeout=.1) 

time.sleep(2) 

temperatura = float(devuelveTemperatura())
humedad = float(devuelveHumedad())

guardarDatos(temperatura,humedad)
 
arduino.close()

