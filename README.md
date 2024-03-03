#### Для сборки и запуска проекта, надо сначала добавить сертификат tilab в хранилище JDK. Для этого:
1. Загрузите сертификат к себе с сайта https://jade.tilab.com/maven/ (через openssl или экспорт в браузере) в формате `.cer`
2. Добавьте его в доверенные при помощи команды `keytool -import -alias example -keystore  "$JAVA_HOME\lib\security\cacerts" -file jade.tilab.com.cer`
3. Перезагрузите JDK или ваш ПК

#### Для запуска в Docker
1. Установите Docker с официального сайта https://www.docker.com/products/docker-desktop/
2. Запустите в корне проекта команду `./gradlew dockerStartContainer`
3. Или запустите `Gradle -> Tasks -> docker -> dockerStartContainer` в меню IntelliJIDEA