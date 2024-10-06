<img src="https://github.com/user-attachments/assets/0a16569c-2480-4af8-bf3b-37b3f8ef58a0" width=120 />

# terning
<p align="center"><img src="https://github.com/user-attachments/assets/984e7795-3746-4e7a-ad6c-cb1cb376c481"></p>

**내 계획에 딱 맞는 대학생 인턴의 시작, 터닝**

취업을 위한 필수 관문이자 대학생으로서 쌓을 수 있는 *최고의 스펙, 인턴.*

학점 관리부터 대외활동까지, 바쁜 일상 속에서 대학생들은 인턴이라는 스펙을 위해 자신만의 계획을 수립합니다.

희망하는 근무 기간과 그 기간 동안 일할 수 있는 기업은 무엇이 있는지, 내가 지원할 공고의 지원 마감일은 언제인지.

팀 터닝포인트는 대학생이 세운 계획에 딱 맞는 인턴 공고를 추천해주고, 인턴 지원 일정을 효율적으로 관리하는 방안에 대해 고민합니다.

터닝에서  **나만의 인턴 계획을 등록**하고, **딱 맞는 인턴 공고를 추천** 받아보세요!

<br> 

## DOWNLOAD
<a href="https://play.google.com/store/apps/details?id=com.terning.point"><img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height="100"></a>


## CONTRIBUTORS
|                                  👑이유빈<br/>([@leeeyubin](https://github.com/leeeyubin))                                    |                                      이석준<br/>([@boiledEgg-s](https://github.com/boiledEgg-s))                                       |                                  김아린<br/>([@arinming](https://github.com/arinming))                                   |                                    박효빈<br/>([@Hyobeen-Park](https://github.com/Hyobeen-Park))                                     |
|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
| <img width="200px" src="https://github.com/user-attachments/assets/fdaf7ee5-6180-4c61-b0f4-3105f700afcf"/> | <img width="200px" src="https://github.com/user-attachments/assets/e5405ebc-c08e-4a50-92c1-e627d8b684e7"/> | <img width="200px" src="https://github.com/user-attachments/assets/fc939187-eb24-468a-8824-7adb790506b6"/>     | <img width="200px" src="https://github.com/user-attachments/assets/f4784902-a4a9-4d9d-aa58-4d83f3979907"/>  |
|                                                      `온보딩`<br/>`마이 페이지`<br/>                                                      |                                                         `캘린더 월간`<br/>`캘린더 주간`<br/>                                                       |                                          `탐색`<br/>`공고 상세 페이지`<br/>                                       |                                                      `홈`<br/>`필터링 재설정`<br/>                                                |




<br>

## DEPENDENCY GRAPH
  <img width="300px" src="https://github.com/user-attachments/assets/9f730e35-2ecf-4d1f-a170-e08a4836fe8a"/>


## MODULE & PACKAGE CONVENTION

```

🗃️app
 ┗ 📂di

🗃️core
 ┣ 📂designsystem
 ┃ ┣ 📂component
 ┃ ┣ 📂theme
 ┣ 📂extension
 ┣ 📂navigation
 ┣ 📂state
 ┣ 📂type
 ┗ 📂util

🗃️data
 ┣ 📂datasource
 ┣ 📂datasourceImpl
 ┣ 📂dto
 ┃ ┣ 📂response
 ┃ ┣ 📂request
 ┣ 📂local
 ┣ 📂mapper
 ┣ 📂repositoryImpl
 ┗ 📂service

🗃️domain
 ┣ 📂entity
 ┗ 📂repository

🗃️feature
 ┗ 📂기능 별 패키징

```

<br>

## TECH STACK
| Title | Content |
| ------------ | -------------------------- |
| Architecture | Clean Architecture, MVI, Multi-Module |
| UI Framework  | Jetpack Compose  |
| Build Tools  | Gradle Version Catalog |
| Dependency Injection | Hilt  |
| Network | Retrofit2, OkHttp  |
| Asynchronous Processing | Coroutine, Flow |
| Third Party Library | Coil, Timber, Kakao SDK, Lottie  |
| Other Tools | Discode, Notion, Figma  |\
</br>

## SCREENSHOTS
|       뷰       |                                                              1                                                              |                                                              2                                                              |                                                              3                                                                                        |
|:-------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
| 온보딩 <br> 필터링 설정 | <img width="200px" src="https://github.com/user-attachments/assets/21b01866-318f-44e9-bc36-84413f2785f9"/> | <img width="200px" src="https://github.com/user-attachments/assets/e246f642-f914-43cc-a7b9-63efbafac71c"/> | <img width="200px" src="https://github.com/user-attachments/assets/5480ce13-3440-4887-a103-1f92321c3e32"/> |                                                                                                                                 |                                                                                                                             |
| 홈 <br> 필터링 재설정 | <img width="200px" src="https://github.com/user-attachments/assets/92d60331-59f6-4259-937a-75ee7cf4bdf2"/> | <img width="200px" src="https://github.com/user-attachments/assets/e3776b7c-1ea9-4e85-9243-174c8e542ec0"/> | <img width="200px" src="https://github.com/user-attachments/assets/61976dea-b36f-44bd-9690-3b9b2fedc1ef"/> |
| 캘린더 월간 <br> 캘린더 주간  | <img width="200px" src="https://github.com/user-attachments/assets/16558280-aeec-45cc-ad08-b9ded7839ca3"/> | <img width="200px" src="https://github.com/user-attachments/assets/707d4ff4-6091-4baa-8587-ce6086bbb9c9"/> | <img width="200px" src="https://github.com/user-attachments/assets/30c6e248-5613-4b6e-9fa9-39ca0e52cfe4"/> | 
|   탐색 <br> 공고 상세 페이지   | <img width="200px" src="https://github.com/user-attachments/assets/0ee60e84-446d-4d3f-8d8e-4d6819116b89"/> | <img width="200px" src="https://github.com/user-attachments/assets/7ba40730-bb8a-4bee-a0d3-bc475d7875df"/> | <img width="200px" src="https://github.com/user-attachments/assets/65ce3b74-3d9f-4857-bc47-0881105efdf6"/> | 
|   마이페이지 <br> 프로필 수정   | <img width="200px" src="https://github.com/user-attachments/assets/66009141-6d48-495d-9242-f891c2d66b91"/> | <img width="200px" src="https://github.com/user-attachments/assets/8ffd5fb8-6e3b-4642-ae0c-8aa21931afbc"/> | <img width="200px" src="https://github.com/user-attachments/assets/25e01ee3-6bc1-4f21-890c-1d31c778c2e8"/> | 

<br>

## DESIGN SYSTEM
🔗 [TERNING DESIGN SYSTEM](https://teamterning.github.io/Terning-Android/index.html)


## KANBAN BOARD
🔗 [TERNING PROJECT](https://github.com/orgs/teamterning/projects/1)

<br>  

## BRANCH STRATEGY
```
작업 유형/#이슈 번호-작업 내용
```

## COMMIT CONVENTION
```
[작업 유형/#이슈 번호] 작업 내용
```

## PR CONVENTION
```
[작업 유형/#이슈 번호] 작업 뷰 / 작업 내용
```
<br>
