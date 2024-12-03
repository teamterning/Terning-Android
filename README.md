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
  <img src="https://github.com/user-attachments/assets/0d88e4fe-b170-4aae-83e6-c9017b2bc893"/>

## MODULE & PACKAGE CONVENTION

```

🗃️app

🗃️build-logic
 ┗ 📂build-logic

🗃️core
 ┣ 🗃️analytics
 ┣ 🗃️designsystem
 ┃ ┣ 📂component
 ┃ ┣ 📂extension
 ┃ ┣ 📂state
 ┃ ┣ 📂theme
 ┃ ┣ 📂type
 ┃ ┣ 📂util
 ┣ 🗃️local
 ┣ 🗃️navigation
 ┗ 🗃️network

🗃️data
 ┣ 🗃️auth
 ┣ 🗃️calendar
 ┣ 🗃️filtering
 ┣ 🗃️home
 ┣ 🗃️intern
 ┣ 🗃️mypage
 ┣ 🗃️scrap
 ┣ 🗃️search
 ┣ 🗃️token
 ┗ 🗃️tokenreissue

🗃️domain
 ┣ 🗃️auth
 ┣ 🗃️calendar
 ┣ 🗃️filtering
 ┣ 🗃️home
 ┣ 🗃️intern
 ┣ 🗃️mypage
 ┣ 🗃️scrap
 ┣ 🗃️search
 ┣ 🗃️token
 ┗ 🗃️tokenreissue

🗃️feature
 ┗ 🗃️기능 별 모듈화

```

<br>

## TECH STACK
| Title | Content                               |
| ------------ |---------------------------------------|
| Architecture | Clean Architecture, MVI, Multi-Module |
| UI Framework  | Jetpack Compose                       |
| Build Tools  | Custom Build Logic, Gradle Version Catalog,                  |
| Dependency Injection | Hilt                                  |
| Network | Retrofit2, OkHttp                     |
| Asynchronous Processing | Coroutine, Flow                       |
| Third Party Library | Coil, Timber, Kakao SDK, Lottie       |
| Other Tools | Discode, Notion, Figma                |\
</br>

## SCREENSHOTS
|       뷰       |                                                              1                                                              |                                                              2                                                              |                                                              3                                                                                        |
|:-------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
| 온보딩 <br> 필터링 설정 | <img width="200px" src="https://github.com/user-attachments/assets/e3b39df0-78f3-4c7b-8049-f5133f18d419"/> | <img width="200px" src="https://github.com/user-attachments/assets/f3cd3efb-b0bc-4bf3-b3ee-0a8be316883a"/> | <img width="200px" src="https://github.com/user-attachments/assets/11d5283c-0537-484a-b822-bcb291e79203"/> |                                                                                                                                 |                                                                                                                             |
| 홈 <br> 필터링 재설정 | <img width="200px" src="https://github.com/user-attachments/assets/4f558239-fde5-41d7-b7d8-6988b3091fe6"/> | <img width="200px" src="https://github.com/user-attachments/assets/5c7d77d3-3cdc-4f30-8b1d-4e1878266f04"/> | <img width="200px" src="https://github.com/user-attachments/assets/d3202b3b-d8fe-42cf-b7aa-3e54be928736"/> |
| 캘린더 월간 <br> 캘린더 주간  | <img width="200px" src="https://github.com/user-attachments/assets/0af96542-a560-41f6-87e1-a063dbc49e7c"/> | <img width="200px" src="https://github.com/user-attachments/assets/5d50db13-40af-4f40-b395-235d1560a023"/> | <img width="200px" src="https://github.com/user-attachments/assets/cfdf0ef3-e299-43ba-8208-97d46b320acf"/> | 
|   탐색 <br> 공고 상세 페이지   | <img width="200px" src="https://github.com/user-attachments/assets/0505ed7f-407a-46dd-a06d-22974da5097a"/> | <img width="200px" src="https://github.com/user-attachments/assets/c7c1ff76-2160-4a96-873e-edd069ddf824"/> | <img width="200px" src="https://github.com/user-attachments/assets/c78b5d07-fbbc-408d-b088-3b94ebc3350e"/> | 
|   마이페이지 <br> 프로필 수정   | <img width="200px" src="https://github.com/user-attachments/assets/53b9fb41-9190-4f21-98fb-416d52703e55"/> | <img width="200px" src="https://github.com/user-attachments/assets/678c19cd-3aa5-4056-a807-82fbfc307dac"/> | <img width="200px" src="https://github.com/user-attachments/assets/1e7311d4-34b6-4a12-907a-5afbf68ef898"/> | 

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
