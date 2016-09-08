Nice Toast
============

NiceToast provides a simple way to show a custom toast within your apps.
You can set a theme like "success", "warning" or "error" to show a toast with different colors.

## Sample Application
Check the sample application on Google Play:

[![Get it on Google Play](https://play.google.com/intl/en_us/badges/images/badge_new.png)](https://play.google.com/store/apps/details?id=com.marcelshimooka.example.nicetoast)

## Sample Usage
``` java
  new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.success_message)
                        .withTheme(NiceToast.THEME_SUCCESS)
                        .withPosition(NiceToast.POSITION_TOP)
                        .withDuration(NiceToast.DURATION_LONG)
                        .build()
                        .show();
```

Add NiceToast to your project
----------------------------
Gradle:
```gradle
compile 'com.marcelshimooka.nicetoast:nicetoast:1.0.2'
```

Maven:
```xml
<dependency>
    <groupId>com.marcelshimooka.nicetoast</groupId>
    <artifactId>nicetoast</artifactId>
    <version>1.0.2</version>
</dependency>
```

#Credits
- [Marcel Shimooka](https://github.com/MarcelShimooka)


License
-------

    Copyright 2016 Marcel Shimooka

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.