package com.terning.data.dto.response

import com.terning.domain.entity.response.ScrapModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScrapResponseDto(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("scraps")
    val scraps: List<Scrap>
) {
    @Serializable
    data class Scrap(
        @SerialName("scrapId")
        val scrapId: Long,
        @SerialName("title")
        val title: String,
        @SerialName("color")
        val color: String
    )
}


fun List<ScrapResponseDto>.toScrapsByDeadlineMap(): Map<String, List<ScrapModel>> {
    return this.flatMap { dto ->
        dto.scraps.map { scrap ->
            dto.deadline to scrap.toScrapModel(dto.deadline)
        }
    }.groupBy(
        { it.first },
        { it.second }
    )
}

private fun ScrapResponseDto.Scrap.toScrapModel(deadline: String) = ScrapModel(
    scrapId = scrapId,
    title = title,
    color = color,
    deadLine = deadline,
    isScrapped = true
)

fun getMockScrapList(year: Int, month: Int): List<ScrapResponseDto> {
    val deadline = month.toString().padStart(2, '0')

    return when (month % 4) {
        0 -> {
            listOf(
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-13",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFED4E54"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 3,
                            title = "제목3",
                            color = "#FF9B64E2"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 4,
                            title = "제목4",
                            color = "#FFF260AC"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-17",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FF45D0CC"
                        ),
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-23",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FF5397F3"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-30",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
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
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-01",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFED4E54"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 3,
                            title = "제목3",
                            color = "#FF9B64E2"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 4,
                            title = "제목4",
                            color = "#FFF260AC"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-02",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FF9B64E2"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 3,
                            title = "제목3",
                            color = "#FFED4E54"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 4,
                            title = "제목4",
                            color = "#FFF260AC"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-12",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FF45D0CC"
                        ),
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-27",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FF5397F3"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-29",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
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
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-02",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFED4E54"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 3,
                            title = "제목3",
                            color = "#FF9B64E2"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 4,
                            title = "제목4",
                            color = "#FFF260AC"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-18",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FF45D0CC"
                        ),
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-20",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FF5397F3"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-29",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
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
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-05",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFED4E54"
                        ),
                        ScrapResponseDto.Scrap(
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
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-11",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FFC4E953"
                        ),
                        ScrapResponseDto.Scrap(
                            scrapId = 2,
                            title = "제목2",
                            color = "#FF45D0CC"
                        ),
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-19",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
                            scrapId = 1,
                            title = "제목1",
                            color = "#FF5397F3"
                        )
                    )
                ),
                ScrapResponseDto(
                    deadline = "${year}-${deadline}-28",
                    scraps = listOf(
                        ScrapResponseDto.Scrap(
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