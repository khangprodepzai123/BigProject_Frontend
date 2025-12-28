# Script de fix loi cai dat Android APK
# Chay script nay truoc khi build va install

Write-Host "=== FIXING ANDROID INSTALLATION ISSUE ===" -ForegroundColor Yellow
Write-Host ""

# Buoc 1: Tim ADB
$adbPaths = @(
    "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe",
    "$env:USERPROFILE\AppData\Local\Android\Sdk\platform-tools\adb.exe",
    "C:\Users\$env:USERNAME\AppData\Local\Android\Sdk\platform-tools\adb.exe",
    "$env:ANDROID_HOME\platform-tools\adb.exe"
)

$adb = $null
foreach ($path in $adbPaths) {
    if (Test-Path $path) {
        $adb = $path
        Write-Host "Found ADB at: $path" -ForegroundColor Green
        break
    }
}

if ($null -eq $adb) {
    Write-Host "ADB not found. Please install Android SDK or add to PATH" -ForegroundColor Yellow
    Write-Host "Continuing with other fixes..." -ForegroundColor Yellow
} else {
    # Buoc 2: Kiem tra thiet bi
    Write-Host ""
    Write-Host "Checking connected devices..." -ForegroundColor Cyan
    & $adb devices
    
    # Buoc 3: Force stop app
    Write-Host ""
    Write-Host "Force stopping app..." -ForegroundColor Cyan
    & $adb shell am force-stop com.example.a65131433_bigproject 2>&1 | Out-Null
    
    # Buoc 4: Uninstall app cu
    Write-Host "Uninstalling old app..." -ForegroundColor Cyan
    & $adb uninstall com.example.a65131433_bigproject 2>&1 | Out-Null
    
    # Buoc 5: Clear app data (neu app van con)
    Write-Host "Clearing app data..." -ForegroundColor Cyan
    & $adb shell pm clear com.example.a65131433_bigproject 2>&1 | Out-Null
    
    Write-Host ""
    Write-Host "Cleanup completed!" -ForegroundColor Green
}

# Buoc 6: Clean va rebuild
Write-Host ""
Write-Host "Cleaning project..." -ForegroundColor Cyan
cd $PSScriptRoot
if (Test-Path "gradlew.bat") {
    .\gradlew.bat clean
    Write-Host ""
    Write-Host "Clean completed!" -ForegroundColor Green
} else {
    Write-Host "gradlew.bat not found" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "=== NEXT STEPS ===" -ForegroundColor Yellow
Write-Host "1. In Android Studio: Build -> Rebuild Project" -ForegroundColor White
Write-Host "2. Then: Run -> Run app" -ForegroundColor White
Write-Host ""
Write-Host "OR manually install APK from:" -ForegroundColor Cyan
Write-Host "  app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor White
Write-Host ""
