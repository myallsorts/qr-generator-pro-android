# QR Generator Pro - Android Notification App

📱 **Android app for monitoring your QR Generator Pro website**

This Android app connects to your QR Generator Pro website and sends notifications about:
- Site uptime and performance
- New user registrations  
- QR code generation activity
- System alerts and issues

## 🚀 Features

- **Real-time Monitoring**: Polls your server every 30 seconds
- **Push Notifications**: Get instant alerts on your phone
- **Site Status**: Check if your website is online
- **Performance Metrics**: Monitor response times and uptime
- **Background Service**: Continues monitoring even when app is closed
- **Auto-start**: Starts monitoring when phone boots up

## 📋 Requirements

- Android 7.0 (API level 24) or higher
- Internet connection
- Notification permissions

## 🔧 Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/qr-generator-pro-android.git
cd qr-generator-pro-android
```

### 2. Build the App
```bash
./gradlew assembleDebug
```

### 3. Install on Device
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 🌐 API Endpoints

The app connects to these endpoints on your server:

- **Status**: `https://qrcodegen.allsortswebdesigners.co.za/api/status`
- **Notifications**: `https://qrcodegen.allsortswebdesigners.co.za/api/notifications`
- **Register Device**: `https://qrcodegen.allsortswebdesigners.co.za/api/register-device`

## 📱 Screenshots

![Main Screen](screenshots/main-screen.png)
![Notifications](screenshots/notifications.png)
![Settings](screenshots/settings.png)

## 🛠️ Development

### Project Structure
```
app/
├── src/main/
│   ├── java/com/allsortswebdesigners/qrgeneratorpro/
│   │   ├── MainActivity.java
│   │   ├── services/
│   │   │   └── NotificationPollingService.java
│   │   └── receivers/
│   │       └── BootReceiver.java
│   ├── res/
│   │   ├── layout/
│   │   ├── values/
│   │   └── drawable/
│   └── AndroidManifest.xml
├── build.gradle
└── proguard-rules.pro
```

### Key Components

- **MainActivity**: Main app interface
- **NotificationPollingService**: Background service for monitoring
- **BootReceiver**: Auto-start service on device boot
- **API Client**: HTTP client for server communication

## 🔄 GitHub Actions

Automated builds and releases are configured in `.github/workflows/`:

- **Build APK**: Automatically builds debug and release APKs
- **Release**: Creates GitHub releases with APK files
- **Tests**: Runs automated tests on pull requests

## 📊 Monitoring

The app monitors:
- ✅ Website uptime
- ⚡ Response times
- 🔒 SSL certificate status
- 📊 User activity
- 🎯 QR code generation stats

## 🚨 Notifications

You'll receive notifications for:
- Site going down/coming back up
- Slow response times (>5 seconds)
- SSL certificate expiring soon
- New user registrations
- High QR code generation activity
- System errors or issues

## 🔧 Configuration

Edit `app/src/main/res/values/strings.xml` to configure:
- Server URL
- Polling interval
- Notification settings

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

- **Website**: https://qrcodegen.allsortswebdesigners.co.za/
- **Email**: support@allsortswebdesigners.co.za
- **Issues**: [GitHub Issues](https://github.com/yourusername/qr-generator-pro-android/issues)

## 🏆 Credits

Built by [Allsorts Web Designers](https://allsortswebdesigners.co.za/) for monitoring QR Generator Pro websites.

---

**Made with ❤️ in South Africa**
