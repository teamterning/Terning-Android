package com.terning.data.dto.response

import com.terning.data.dto.response.ScrapResponseDto.ScrapByDeadline
import com.terning.domain.entity.response.ScrapModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScrapResponseDto(
    @SerialName("scrapsByDeadline")
    val scrapsByDeadline: List<ScrapByDeadline>,
) {
    @Serializable
    data class ScrapByDeadline(
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
            @SerialName("deadline")
            val deadline: String,
            @SerialName("color")
            val color: String
        )
    }
}

fun ScrapResponseDto.toScrapsByDeadLineList() = scrapsByDeadline.map { it.toScrapModelList() }

private fun ScrapByDeadline.toScrapModelList() = scraps.map { it.toScrapModel() }

private fun ScrapByDeadline.Scrap.toScrapModel() = ScrapModel(
    scrapId = scrapId,
    title = title,
    deadLine = deadline,
    color = color,
    isScrapped = true
)



fun ScrapResponseDto.toScrapModelList(): List<ScrapModel> {
    return with(mutableListOf<ScrapModel>()) {
        apply {
            for (items in this@toScrapModelList.scrapsByDeadline) {
                for(item in items.scraps) {
                    add(
                        ScrapModel(
                            scrapId = item.scrapId,
                            title = item.title,
                            deadLine = item.deadline,
                            color = item.color,
                            isScrapped = true
                        )
                    )
                }
            }
        }
    }
}

fun getMockScrapList(month: Int): ScrapResponseDto {
    val deadline = month.toString().padStart(2, '0')
    return ScrapResponseDto(
        scrapsByDeadline = listOf(
            ScrapByDeadline(
                deadline = "2024-${deadline}-13",
                scraps = listOf(
                    ScrapByDeadline.Scrap(
                        scrapId = 1,
                        title = "제목1",
                        deadline = "2024-${deadline}-13",
                        color = "0xFFED4E54"
                    ),
                    ScrapByDeadline.Scrap(
                        scrapId = 2,
                        title = "제목2",
                        deadline = "2024-${deadline}-13",
                        color = "0xFFC4E953"
                    ),
                    ScrapByDeadline.Scrap(
                        scrapId = 3,
                        title = "제목3",
                        deadline = "2024-${deadline}-13",
                        color = "0xFF9B64E2"
                    ),
                    ScrapByDeadline.Scrap(
                        scrapId = 4,
                        title = "제목4",
                        deadline = "2024-${deadline}-13",
                        color = "0xFFF260AC"
                    )
                )
            ),
            ScrapByDeadline(
                deadline = "2024-${deadline}-17",
                scraps = listOf(
                    ScrapByDeadline.Scrap(
                        scrapId = 1,
                        title = "제목1",
                        deadline = "2024-${deadline}-17",
                        color = "0xFFC4E953"
                    ),
                    ScrapByDeadline.Scrap(
                        scrapId = 2,
                        title = "제목2",
                        deadline = "2024-${deadline}-17",
                        color = "0xFF45D0CC"
                    ),
                )
            ),
            ScrapByDeadline(
                deadline = "2024-${deadline}-23",
                scraps = listOf(
                    ScrapByDeadline.Scrap(
                        scrapId = 1,
                        title = "제목1",
                        deadline = "2024-${deadline}-23",
                        color = "0xFF5397F3"
                    )
                )
            ),
            ScrapByDeadline(
                deadline = "2024-${deadline}-30",
                scraps = listOf(
                    ScrapByDeadline.Scrap(
                        scrapId = 1,
                        title = "제목1",
                        deadline = "2024-${deadline}-30",
                        color = "0xFF45D0CC"
                    )
                )
            )
        )
    )
}