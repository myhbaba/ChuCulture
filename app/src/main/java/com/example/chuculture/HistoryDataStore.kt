package com.example.chuculture

import com.example.chuculture.model.History

class HistoryDataStore {
    private val historyList= mutableListOf<History>()
    fun generateData(): MutableList<History> {
        val history1=History(R.drawable.history_1_back,R.drawable.history_1,"楚人建国丹阳至周平王三十一年(公元前740年)是楚文化的滥觞期。在这一时期，楚国完成了由原始社会向奴隶社会的转变，在兼采华夏文化和" +
                "蛮夷文化之长的基础上，开始创造自己的特色文化。总的来说，此时的楚文化与华夏文化尚没有明显而重大的区别，尤其是陶器" +
                "、青铜器等物质文化方面，但楚文化又毕竟显示出了一些自己的特性。崇火崇凤好巫、开拓进取、不拘礼法、爱标新立异等特点为后来楚文化的发展奠定了基础。 " +
                "","滥觞期")
        val history2=History(R.drawable.history_2_back,
            R.drawable.history_2,
            "春秋时期是楚文化的勃兴期。这个时期，楚文化开始呈现出自己鲜明的特点，表现在各个方面。从社会形态上看，农奴制布满了楚国各个县邑，奴隶制相反没有得到充分的发展。在民族政策上，楚人把自己定位于夷夏之间，" +
                    "实行抚夷属夏的国策。对于所灭之国，楚人的作法是迁其公室，存其宗庙，县其疆土，抚其臣民，用其贤能，而不以俘掠奴隶为好。从官制上看，楚官多称尹，从中央到地方，" +
                    "少有例外；从行政建制上，楚国首创县制，后为各国效仿；在兵制上，称谓、建制也与中原多异。这些大的文化背景已经充分昭示了楚文化的个性与气魄，楚文化也由" +
                    "此成熟起来。 ",
            "勃兴期")
        val history3=History(R.drawable.history_4_back,
            R.drawable.history_4,
            "战国时期是楚文化的鼎盛期。此时，铜器生产登峰造极，铁器普遍推广，丝织刺绣兴旺发达，漆器木器应运而生，城市建设欣欣向荣，物质文" +
                    "化可算是达到了顶点。与勃兴期明显不同的是，鼎盛期的楚国精神文化大放异彩，给予后世巨大的启迪，从哲学到文学，从字画到乐舞，无不独风骚，彪炳千秋，更为主要的是，楚人的政治社会体制、民族文化心理，日常生活方式等趋于成熟定型，使楚文化真正成了独特的“这一个”，千百年来为人们津津乐道。 ",
            "鼎盛期"
            )
        val history4=History(R.drawable.history_5_back,
            R.drawable.history_5,
            "秦汉之际为楚文化的转变期。秦国灭掉了楚国，并排斥楚文化，使楚文化遭受重大打击。但楚文化的精神因素方面远比物质因素方面根深蒂固，因此楚文化被打而不倒，秦末，楚人刘邦" +
                    "、项羽大兴楚文化，使楚文化有复苏之势，但离开了物质文化根基的楚文化终究是不可能沿着自己的道路发展下去了。汉武帝独尊儒术后，自成体系的楚文化不复存在，但楚文化的某些因子却与传统中原文化一样，成为新的汉文化的组成部分。至此，南北文化相互交融，彼此促进，共同创造着中华民族的灿烂文明。 ",
            "转变期"
        )
        historyList.add(history1)
        historyList.add(history2)
        historyList.add(history3)
        historyList.add(history4)
        return historyList
    }
}