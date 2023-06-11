FROM openjdk
WORKDIR /mybank
COPY . /mybank
RUN javac -d . mybank/Main.java
CMD ["java", "-classpath" , ".:sqlite-jdbc-3.7.2.jar", "mybank/Main"]