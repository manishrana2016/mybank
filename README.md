# Java Based Bank Software (Fully Command Line)
This is a Java based Bank administration software with following functionalities:
1. Account Creation
2. Account Closure
3. Cash Deposit
4. Withdrawal
5. Show Balance
6. Account Information

## Install
```
$ git clone https://github.com/manishrana2016/mybank.git
```

## Compile
```
$ cd mybank-main
$ javac -d . mybank/Main.java
```

## Run
For windows:
```
$ java -classpath ".;sqlite-jdbc-3.7.2.jar" mybank/Main
```

For Linux:
```
$ java -classpath ".:sqlite-jdbc-3.7.2.jar" mybank/Main
```
