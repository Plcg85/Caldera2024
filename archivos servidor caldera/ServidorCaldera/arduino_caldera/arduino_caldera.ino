#include <OneWire.h>
#include <DallasTemperature.h>

OneWire ourWire(2);  //Se establece el pin 3  como bus OneWire

const int pinRele = 5;  //RELE
int iniciado = 0; //Rele

DallasTemperature sensors(&ourWire);  //Se declara una variable u objeto para nuestro sensor

String x;

void setup() {
  Serial.begin(9600);
  sensors.begin();       //Se inicia el sensor
  pinMode(pinRele, OUTPUT);  ///RELE
  Serial.setTimeout(1);
}

void loop() {

  if (iniciado == 0){
    digitalWrite(pinRele, HIGH);
  }

  delay(100);

  while (!Serial.available());
  x = Serial.readString();
  iniciado = 1;

  if (x == "a") {
    sensors.requestTemperatures();            //Se envía el comando para leer la temperatura
    float temp = sensors.getTempCByIndex(0);  //Se obtiene la temperatura en ºC

    Serial.print(temp);
  }

  if (x == "b") { //apaga
    digitalWrite(pinRele, HIGH);
  }

  if (x == "c") {//enciende
    digitalWrite(pinRele, LOW);
  }
}