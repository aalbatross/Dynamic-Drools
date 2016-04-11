Dynamic Drools is a dynamic rule management system which allows to add and remove rules dynamically without stopping Drools Engine

Running without DynamicDrools
RUN DroolsWithoutDynamicDrools.java
mvn -e exec:java -Dexec.mainClass="org.DroolsWithoutDynamicRules"

Running instruction:
RUN JDrools.java as Server
mvn -e exec:java -Dexec.mainClass="org.JDrools"

RUN DroolsClient.java as Client
mvn -e exec:java -Dexec.mainClass="org.DroolsClient"


RULES are files with .drl extension (use them to add or remove dynamically)

video : https://drive.google.com/file/d/0B6IIcZXNUCA3YWhVSlVTTUxDOU0/view
