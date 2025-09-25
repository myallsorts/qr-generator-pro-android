#!/bin/bash

# Setup script for QR Generator Pro Android App GitHub Repository
echo "🚀 Setting up QR Generator Pro Android App for GitHub..."

# Initialize Git repository
echo "📦 Initializing Git repository..."
git init

# Add all files
echo "📁 Adding files to Git..."
git add .

# Create initial commit
echo "💾 Creating initial commit..."
git commit -m "Initial commit: QR Generator Pro Android App

- Complete Android project structure
- Main activity with notification controls
- Background service for website monitoring
- GitHub Actions CI/CD pipeline
- Comprehensive README and documentation
- API endpoints for server communication

Features:
✅ Real-time website monitoring
✅ Push notifications
✅ Background service
✅ Auto-start on boot
✅ Performance metrics tracking
✅ Modern Material Design UI"

# Create main branch
echo "🌿 Creating main branch..."
git branch -M main

echo ""
echo "🎉 GitHub repository setup complete!"
echo ""
echo "📋 Next steps:"
echo "1. Create a new repository on GitHub:"
echo "   https://github.com/new"
echo "   Repository name: qr-generator-pro-android"
echo "   Description: Android notification app for QR Generator Pro website monitoring"
echo "   Make it public or private as needed"
echo ""
echo "2. Add the remote origin:"
echo "   git remote add origin https://github.com/YOUR_USERNAME/qr-generator-pro-android.git"
echo ""
echo "3. Push to GitHub:"
echo "   git push -u origin main"
echo ""
echo "4. Build the Android app:"
echo "   ./gradlew assembleDebug"
echo ""
echo "5. Install on your device:"
echo "   adb install app/build/outputs/apk/debug/app-debug.apk"
echo ""
echo "🔗 Your website: https://qrcodegen.allsortswebdesigners.co.za/"
echo "📱 API endpoints:"
echo "   - Status: https://qrcodegen.allsortswebdesigners.co.za/status.json"
echo "   - Notifications: https://qrcodegen.allsortswebdesigners.co.za/notifications.json"
echo ""
echo "✨ Happy coding!"
