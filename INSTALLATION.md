# 🚀 QR Generator Pro Android App - Installation Guide

## ✅ What's Already Done

Your Android app has been successfully created and pushed to GitHub:
- **Repository**: https://github.com/myallsorts/qr-generator-pro-android
- **Complete project structure** with all necessary files
- **Main Activity** with notification controls
- **Background Service** for website monitoring
- **Boot Receiver** for auto-start functionality
- **Modern Material Design UI** with your brand colors

## 📱 Next Steps to Build & Install

### 1. Install Java Development Kit (JDK 17)
```bash
# On Ubuntu/Debian:
sudo apt update
sudo apt install openjdk-17-jdk

# On macOS (with Homebrew):
brew install openjdk@17

# On Windows:
# Download from: https://adoptium.net/temurin/releases/
```

### 2. Set JAVA_HOME Environment Variable
```bash
# Add to your ~/.bashrc or ~/.zshrc:
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Then reload:
source ~/.bashrc
```

### 3. Build the Android App
```bash
cd "/home/allsorts/Local Sites/qrcode/app/public/qr-generator-pro-android"
./gradlew assembleDebug
```

### 4. Install on Your Android Device
```bash
# Enable Developer Options on your Android device
# Enable USB Debugging
# Connect device via USB
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 🔧 Alternative: Use Android Studio

1. **Download Android Studio**: https://developer.android.com/studio
2. **Clone the repository**:
   ```bash
   git clone https://github.com/myallsorts/qr-generator-pro-android.git
   ```
3. **Open in Android Studio**
4. **Build and Run** directly to your device

## 📋 Features Included

- ✅ **Real-time Website Monitoring** (polls every 30 seconds)
- ✅ **Push Notifications** for site status changes
- ✅ **Background Service** continues monitoring when app is closed
- ✅ **Auto-start** on device boot
- ✅ **Modern UI** with Material Design
- ✅ **Server Status Checking** with visual indicators
- ✅ **Test Notifications** to verify functionality
- ✅ **Direct Website Access** button

## 🌐 API Endpoints

The app connects to these endpoints on your server:
- **Status**: `https://qrcodegen.allsortswebdesigners.co.za/status.json`
- **Notifications**: `https://qrcodegen.allsortswebdesigners.co.za/notifications.json`

## 🔔 What You'll Get Notified About

- Site going down/coming back up
- Slow response times (>5 seconds)
- SSL certificate expiring soon
- New user registrations
- High QR code generation activity
- System errors or issues

## 🎯 GitHub Repository

**Repository**: https://github.com/myallsorts/qr-generator-pro-android

### To Add GitHub Actions (Optional):
1. Go to your repository on GitHub
2. Go to **Actions** tab
3. Create a new workflow file: `.github/workflows/build.yml`
4. Copy the content from the workflow file in the project

## 🚨 Troubleshooting

### Java Not Found
```bash
# Check if Java is installed:
java -version

# If not installed, install JDK 17:
sudo apt install openjdk-17-jdk
```

### Gradle Build Fails
```bash
# Clean and rebuild:
./gradlew clean
./gradlew assembleDebug
```

### ADB Not Found
```bash
# Install Android SDK Platform Tools:
sudo apt install android-sdk-platform-tools
```

## 📞 Support

- **Website**: https://qrcodegen.allsortswebdesigners.co.za/
- **GitHub Issues**: https://github.com/myallsorts/qr-generator-pro-android/issues
- **Email**: support@allsortswebdesigners.co.za

---

**🎉 Your Android notification app is ready! Just install Java and build it!**
