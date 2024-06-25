# Currency Converter

## Overview
Currency Converter is an Android application that converts currency rates using the ExchangeRate API. Built with Kotlin, this app demonstrates the use of Retrofit for API calls, Dagger-Hilt for dependency injection, and MVVM architecture.

<div align="center">
    <img src="https://github.com/pronob-kumar-mondol/Currency-Converter/raw/main/For%20gif.gif" alt="App Demo"/>
</div>


## Features
- Convert currency rates in real-time
- Simple and user-friendly interface
- Error handling for network requests
- MVVM architecture
- Dependency injection with Dagger-Hilt

## Tech Stack
- Kotlin
- Retrofit
- Dagger-Hilt
- MVVM Architecture
- Coroutines
- StateFlow
- ViewModel

## Setup

### Prerequisites
- Android Studio
- Kotlin 1.9
- Gradle 6.5+

### Installation
1. **Clone the repository**
    ```bash
    git clone https://github.com/your-username/currency-converter.git
    cd currency-converter
    ```

2. **Open the project in Android Studio**
    - Open Android Studio.
    - Select `Open an existing Android Studio project`.
    - Navigate to the cloned directory and click `OK`.

3. **Add your API key**
    - Obtain your API key from [ExchangeRate API](https://www.exchangerate-api.com/).
    - Replace the `YOUR_API_KEY` placeholder in the `CurrencyApi` interface with your actual API key.

    ```kotlin
    private const val BASE_URL = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/"
    ```

4. **Build and Run**
    - Click on the `Run` button in Android Studio to build and run the app.

## Usage

1. **Select Base Currency**
   - Choose the base currency from the dropdown list.

2. **Enter Amount**
   - Enter the amount you want to convert.

3. **Select Target Currency**
   - Choose the target currency from the dropdown list.

4. **Convert**
   - Click the `Convert` button to see the converted amount.

## Contributing
1. **Fork the repository**
2. **Create a new branch**
    ```bash
    git checkout -b feature-branch
    ```
3. **Make your changes**
4. **Commit your changes**
    ```bash
    git commit -m "Add some feature"
    ```
5. **Push to the branch**
    ```bash
    git push origin feature-branch
    ```
6. **Create a Pull Request**

## Contact
- **Author:** Pronob Kumar Mondol
- **Email:** pronob2055@gmail.com
- **GitHub:** [pronob-kumar-mondol](https://github.com/pronob-kumar-mondol)
