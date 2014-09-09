jmeter-xmpp-plugin
==================

# Usage
You can build plugin and run jmeter with it, by running maven command:
```
mvn package antrun:run -Djmeter.executable=<full path to jmeter executable file>
```

# Description
## XmppController

# JMeter classpath issue
Latest Jmeter release (2.11) has issue in the code (JMeter.java). They trying to parse search_paths value with ';' instead of using system path.separator variable. That is why we are replacing all ':' with ';' in ant task before run jmeter:
```
   <loadresource property="runtime_classpath_fixed">
      <propertyresource name="runtime_classpath"/>
      <filterchain>
          <tokenfilter>
              <filetokenizer/>
              <replacestring from=":" to=";"/>
          </tokenfilter>
      </filterchain>
  </loadresource>
```
