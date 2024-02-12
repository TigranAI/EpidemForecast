#### Для сборки и запуска проекта, надо сначала добавить сертификат tilab в хранилище JDK. Для этого:
1. Загрузите сертификат к себе с сайта https://jade.tilab.com/maven/ (через openssl или экспорт в браузере) в формате `.cer`
2. Добавьте его в доверенные при помощи команды `keytool -import -alias example -keystore  "$JAVA_HOME\lib\security\cacerts" -file jade.tilab.com.cer`
3. Перезагрузите JDK или ваш ПК