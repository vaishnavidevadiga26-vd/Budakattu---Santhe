Budakattu Sante – Android App

Budakattu Sante is a modern Android application designed to support tribal farmers, artisans, and local market systems. It enables direct selling of handmade products, forest produce, and traditional items to customers without middlemen, helping improve income and promote sustainable rural development.

Features:
Secure user onboarding and login system
Leader dashboard for market management
Product listing with detailed information
Pre-order management system
Customer management module
Supply tracking and logging
Transaction and payment records
MSP (Minimum Support Price) information display
User profile and settings management
Offline-first storage using Room Database
Background synchronization using WorkManager

Tech Stack:
Language: Kotlin
UI: XML Layouts with Material Design
Architecture: MVVM + Repository Pattern
Database: Room (DAO-based local storage)
Navigation: Jetpack Navigation Component
Background Tasks: WorkManager
Dependency Injection: Hilt

Project Structure:

com.example.budakattu_sante

data/ → Local database, DAO, repository
di/ → Dependency injection modules
ui/ → Fragments, adapters, ViewModels
util/ → Helper and utility classes
worker/ → Background sync tasks

Setup Instructions:
1. Clone the Repository
git clone https://github.com/malikarshid01430-byte/Budakattu-Sante.git

2. Open in Android Studio:
Open Android Studio
Click Open Project
Select the extracted folder
Wait for Gradle sync to complete
Click Run to launch the app
Purpose

This project aims to digitally empower tribal communities by connecting them directly with customers, reducing dependency on middlemen, and promoting fair trade, cultural preservation, and sustainable livelihoods.

Future Improvements:
Firebase integration for cloud sync
Online payment gateway support
Real-time notifications
Admin web dashboard
Multi-language support for tribal regions
Advanced analytics and reporting
Contributing
Fork the repository
Create a feature branch
Commit your changes
Push and create a pull request

License:

This project is developed for academic and internship purposes.

Developer:

Developed by Arshid Ahmad Malik

Support:

If you find this project useful, consider giving it a star on GitHub.
