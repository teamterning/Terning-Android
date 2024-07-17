package com.terning.data.datasourceimpl

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarDayListResponseDto
import com.terning.data.dto.response.CalendarMonthListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto
import com.terning.data.service.CalendarService

class TempCalendarService: CalendarService {
    override suspend fun getCalendarScrapMonth(
        year: Int,
        month: Int
    ): BaseResponse<List<CalendarMonthResponseDto>> = CalendarList.getCalendarScrapMonth(
        CalendarMonthRequestDto(year, month)
    )

    override suspend fun getCalendarScrapMonthList(
        year: Int,
        month: Int
    ): BaseResponse<List<CalendarMonthListResponseDto>> = CalendarList.getCalendarScrapMonthList(
        CalendarMonthListRequestDto(year, month)
    )

    override suspend fun getCalendarScrapDayList(
        date: String
    ): BaseResponse<List<CalendarDayListResponseDto>> = CalendarList.getCalendarScrapDayList(
        CalendarDayListRequestDto(date)
    )

}

object CalendarList {
    fun getCalendarScrapMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>> =
        BaseResponse(
            status = 200,
            message = "캘린더 > (월간) 스크랩 된 공고 정보 불러오기를 성공했습니다",
            result = getMockScrapList(request.year, request.month)
    )

    fun getCalendarScrapMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarMonthListResponseDto>> =
        BaseResponse(
            status = 200,
            message = "캘린더 > (월간) 스크랩 된 공고 정보 불러오기를 성공했습니다",
            result = getMockScrapDetailList(request.year, request.month)
    )

    fun getCalendarScrapDayList(requestDto: CalendarDayListRequestDto): BaseResponse<List<CalendarDayListResponseDto>> =
        BaseResponse(
            status = 200,
            message = "캘린더 > (일간) 스크랩 된 공고 정보 불러오기를 성공했습니다",
            result = getMockDayList(requestDto.date)
    )



    fun getMockScrapList(year: Int, month: Int): List<CalendarMonthResponseDto> {
        val deadline = month.toString().padStart(2, '0')
        return when (month % 4) {
            0 -> {
                listOf(
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-13",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFED4E54"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 3,
                                title = "제목3",
                                color = "#FF9B64E2"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 4,
                                title = "제목4",
                                color = "#FFF260AC"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-17",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FF45D0CC"
                            ),
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-23",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF5397F3"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-30",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF45D0CC"
                            )
                        )
                    )
                )
            }

            1 -> {
                listOf(
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-01",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFED4E54"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 3,
                                title = "제목3",
                                color = "#FF9B64E2"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 4,
                                title = "제목4",
                                color = "#FFF260AC"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-02",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FF9B64E2"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 3,
                                title = "제목3",
                                color = "#FFED4E54"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 4,
                                title = "제목4",
                                color = "#FFF260AC"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-12",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FF45D0CC"
                            ),
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-27",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF5397F3"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-29",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF45D0CC"
                            )
                        )
                    )
                )
            }

            2 -> {
                listOf(
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-02",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFED4E54"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 3,
                                title = "제목3",
                                color = "#FF9B64E2"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 4,
                                title = "제목4",
                                color = "#FFF260AC"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-18",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FF45D0CC"
                            ),
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-20",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF5397F3"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-29",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF45D0CC"
                            )
                        )
                    )
                )
            }

            3 -> {
                listOf(
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-05",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFED4E54"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FFC4E953"
                            )/*,
                        ScrapResponsesDto.Scrap(
                            scrapId = 3,
                            title = "제목3",
                            color = "#FF9B64E2"
                        ),
                        ScrapResponsesDto.Scrap(
                            scrapId = 4,
                            title = "제목4",
                            color = "#FFF260AC"
                        )*/
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-11",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FFC4E953"
                            ),
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 2,
                                title = "제목2",
                                color = "#FF45D0CC"
                            ),
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-19",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF5397F3"
                            )
                        )
                    ),
                    CalendarMonthResponseDto(
                        deadline = "${year}-${deadline}-28",
                        scraps = listOf(
                            CalendarMonthResponseDto.Scrap(
                                scrapId = 1,
                                title = "제목1",
                                color = "#FF45D0CC"
                            )
                        )
                    )
                )
            }

            else -> {
                listOf()
            }
        }
    }
}


/*************************MockDataType***************************/

fun getMockScrapDetailList(year: Int, month: Int): List<CalendarMonthListResponseDto> {
    val deadline = month.toString().padStart(2, '0')
    return when (month % 5) {
        0 -> {
            listOf(
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-13",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFED4E54",
                            internshipAnnouncementId = 1,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 2,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 3,
                            title = "제목3",
                            color = "#FF9B64E2",
                            internshipAnnouncementId = 3,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 4,
                            title = "제목4",
                            color = "#FFF260AC",
                            internshipAnnouncementId = 3,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-17",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 5,
                            title = "제목5",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 5,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 6,
                            title = "제목6",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 6,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-23",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 7,
                            title = "제목7",
                            color = "#FF5397F3",
                            internshipAnnouncementId = 7,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-30",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 8,
                            title = "제목8",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 8,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                )
            )
        }

        1 -> {
            listOf(
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-01",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 9,
                            title = "제목9",
                            color = "#FFED4E54",
                            internshipAnnouncementId = 9,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 10,
                            title = "제목10",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 10,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 11,
                            title = "제목11",
                            color = "#FF9B64E2",
                            internshipAnnouncementId = 11,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 12,
                            title = "제목12",
                            color = "#FFF260AC",
                            internshipAnnouncementId = 12,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-02",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 13,
                            title = "제목13",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 13,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 14,
                            title = "제목14",
                            color = "#FF9B64E2",
                            internshipAnnouncementId = 14,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 15,
                            title = "제목15",
                            color = "#FFED4E54",
                            internshipAnnouncementId = 15,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 16,
                            title = "제목16",
                            color = "#FFF260AC",
                            internshipAnnouncementId = 16,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-12",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 17,
                            title = "제목17",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 17,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 18,
                            title = "제목18",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 18,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-27",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 19,
                            title = "제목19",
                            color = "#FF5397F3",
                            internshipAnnouncementId = 19,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-29",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 20,
                            title = "제목20",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 20,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                )
            )
        }

        2 -> {
            listOf(
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-02",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 21,
                            title = "제목1",
                            color = "#FFED4E54",
                            internshipAnnouncementId = 21,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 22,
                            title = "제목22",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 22,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 23,
                            title = "제목23",
                            color = "#FF9B64E2",
                            internshipAnnouncementId = 23,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 24,
                            title = "제목24",
                            color = "#FFF260AC",
                            internshipAnnouncementId = 24,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-18",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 25,
                            title = "제목25",
                            color = "#FFC4E953",
                            internshipAnnouncementId =25,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 26,
                            title = "제목26",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 26,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-20",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 27,
                            title = "제목27",
                            color = "#FF5397F3",
                            internshipAnnouncementId = 27,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-29",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 28,
                            title = "제목28",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 28,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                )
            )
        }

        3 -> {
            listOf(
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-05",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 29,
                            title = "제목29",
                            color = "#FFED4E54",
                            internshipAnnouncementId = 29,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 30,
                            title = "제목30",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 30,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-11",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 31,
                            title = "제목31",
                            color = "#FFC4E953",
                            internshipAnnouncementId = 31,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 32,
                            title = "제목32",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 32,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        ),
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-19",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 33,
                            title = "제목33",
                            color = "#FF5397F3",
                            internshipAnnouncementId = 33,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                ),
                CalendarMonthListResponseDto(
                    deadline = "${year}-${deadline}-28",
                    scraps = listOf(
                        CalendarMonthListResponseDto.Scrap(
                            scrapId = 34,
                            title = "제목34",
                            color = "#FF45D0CC",
                            internshipAnnouncementId = 34,
                            dDay = "10",
                            workingPeriod = "10",
                            companyImage = "",
                            startYear = 2024,
                            startMonth = 8
                        )
                    )
                )
            )
        }

        else -> {
            listOf()
        }
    }
}

/******************DayList******************/

fun getMockDayList(request: String): List<CalendarDayListResponseDto> = listOf(
    CalendarDayListResponseDto(
        scrapId = 34,
        title = "제목34",
        color = "#FF45D0CC",
        internshipAnnouncementId = 34,
        dDay = "10",
        workingPeriod = "10",
        companyImage = "",
        startYear = 2024,
        startMonth = 8
    ),
    CalendarDayListResponseDto(
        scrapId = 34,
        title = "제목34",
        color = "#FF45D0CC",
        internshipAnnouncementId = 34,
        dDay = "10",
        workingPeriod = "10",
        companyImage = "",
        startYear = 2024,
        startMonth = 8
    )
)
