// Paso 1
#include "DHT.h"

//Paso 2
#define DHTPIN 2
#define DHTTYPE DHT21  // DHT21 (AM2301)

String resultado = "";  //parar devolver los valores de temperatura y humedad

DHT dht(DHTPIN, DHTTYPE);
String x;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  dht.begin();
  Serial.setTimeout(1);
}

void loop() {
  // put your main code here, to run repeatedly:

  delay(100);

  while (!Serial.available());
  x = Serial.readString();
  Serial.print(x);

  if (x == "a") {

    float h = dht.readHumidity();
    float t = dht.readTemperature();
    float f = dht.readTemperature(true);

    if (isnan(h) || isnan(t) || isnan(f)) {

      //Serial.println("Failed to read from DHT sensor!");
      return;

    } else {

      Serial.print(t);

    }  //final si falla
  }    //final si la entrada es 1

  if (x == "b") {

    float h = dht.readHumidity();
    float t = dht.readTemperature();
    float f = dht.readTemperature(true);

    if (isnan(h) || isnan(t) || isnan(f)) {

      //Serial.println("Failed to read from DHT sensor!");
      return;

    } else {

      Serial.print(h);

    }  //final si falla

  }  //final si entrrada es 2

}  //final loop
