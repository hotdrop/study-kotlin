package study.implementation

import study.AbstractStudy

class ImplAlgorithms : AbstractStudy(ImplAlgorithms::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 16
        when (targetNo) {
            1 -> execBeautifulDaysAtTheMovies()
            2 -> execViralAdvertising()
            3 -> execSaveThePrisoner()
            4 -> execCircularArrayRotation()
            5 -> execSequenceEquation()
            6 -> execJumpingOnTheClouds()
            7 -> execFindDigits()
            8 -> exeCutTheSticks()
            9 -> execRepeatedString()
            10 -> execLibraryFine()
            11 -> execEqualizeArray()
            12 -> execJumpingOnTheCloud2()
            13 -> execAcmTeam()
            14 -> execTotalPayCost()
            15 -> execMinimumDistances()
            16 -> execHalloweenSale()
        }
    }

    private fun execBeautifulDaysAtTheMovies() {
        val res = beautifulDays(20, 23, 6)
        println(res)
    }

    private fun beautifulDays(i: Int, j: Int, k: Int): Int {

        fun isBeautiful(day: Int, div: Int): Boolean =
                day.toString()
                        .reversed()
                        .toInt()
                        .let { ((day - it) % div) == 0 }

        fun countBeautifulDay(startDay: Int, endDay: Int, div: Int) =
                (startDay..endDay).count { isBeautiful(it, div) }

        return countBeautifulDay(i, j, k)
    }

    private fun execViralAdvertising() {
        val res = viralAdvertising(10)
        println(res)
    }

    private fun viralAdvertising(n: Int): Int {
        var sharedNum = 5
        var liked: Int
        var cumulative = 0
        (1..n).forEach {
            liked = sharedNum / 2
            cumulative += liked
            sharedNum = liked * 3
        }
        return cumulative
    }

    private fun execSaveThePrisoner() {
        saveThePrisoner(5, 2, 1).run { if (this != 2) throw IllegalStateException("case1 $this != 2") }
        saveThePrisoner(7, 19, 2).run { if (this != 6) throw IllegalStateException("case2 $this != 6") }
        saveThePrisoner(100, 1, 10).run { if (this != 10) throw IllegalStateException("case3 $this != 10") }
        saveThePrisoner(5, 11, 1).run { if (this != 1) throw IllegalStateException("case4 $this != 1") }
        saveThePrisoner(8, 3, 1).run { if (this != 3) throw IllegalStateException("case5 $this != 3") }
        saveThePrisoner(1, 5938385, 1).run { if (this != 1) throw IllegalStateException("case6 $this != 1") }
        saveThePrisoner(784893322, 849791807, 360911386).run { if (this != 425809870) throw IllegalStateException("case7 $this != 425809870") }
        saveThePrisoner(499999999, 999999997, 2).run { if (this != 499999999) throw IllegalStateException("case8 $this != 499999999") }
        saveThePrisoner(499999999, 999999998, 2).run { if (this != 1) throw IllegalStateException("case9 $this != 1") }
        saveThePrisoner(999999999, 999999999 ,1).run { if (this != 999999999) throw IllegalStateException("case10 $this != 999999999") }
        saveThePrisoner(352926151, 380324688, 94730870).run { if (this != 122129406) throw IllegalStateException("case11 $this != 122129406") }
        saveThePrisoner(649320641, 742902564, 647542323).run { if (this != 91803604) throw IllegalStateException("case12 $this != 91803604") }
    }

    private fun saveThePrisoner(n: Int, m: Int, s: Int): Int =
        when {
            m == 1 || n == 1 -> s
            s == 1 && n >= m -> m
            else -> {
                (m % n).let { rem ->
                    (rem + (s - 1)).let {
                        if (it <= n) {
                            it
                        } else {
                            it - n
                        }
                    }
                }
            }
        }

    private fun execCircularArrayRotation() {
        val results = circularArrayRotation(arrayOf(1,2,3), 2, arrayOf(0,1,2))
        if (results[0] == 2 && results[1] == 3 && results[2] == 1) {
            println("Success!")
        } else {
            println("Failure...${results[0]} ${results[1]} ${results[2]}")
        }
    }

    private fun circularArrayRotation(a: Array<Int>, k: Int, queries: Array<Int>): Array<Int> {
        val rotate = if (a.size < k) k % a.size else k
        return (a.takeLast(rotate) + a.copyOfRange(0, a.size - rotate)).let { arrayWithRotation ->
            queries.map {
                arrayWithRotation[it]
            }.toTypedArray()
        }
    }

    private fun execSequenceEquation() {

        fun checkResults(success: Boolean) {
            if (success) {
                println("Success!")
            } else {
                throw IllegalStateException("Failure...")
            }
        }

        var success = true
        arrayOf(4, 2, 5, 1, 3).zip(sequenceEquation(arrayOf(5, 2, 1, 3, 4))).forEach {
            if (it.first != it.second) {
                println("Not Answer! Answer = ${it.first} calcResult = ${it.second}")
                success = false
            }
        }
        checkResults(success)
        arrayOf(2, 3, 1).zip(sequenceEquation(arrayOf(2, 3, 1))).forEach {
            if (it.first != it.second) {
                println("Not Answer! Answer = ${it.first} calcResult = ${it.second}")
                success = false
            }
        }
        checkResults(success)
        arrayOf(1, 3, 5, 4, 2).zip(sequenceEquation(arrayOf(4, 3, 5, 1, 2))).forEach {
            if (it.first != it.second) {
                println("Not Answer! Answer = ${it.first} calcResult = ${it.second}")
                success = false
            }
        }

    }

    private fun sequenceEquation(p: Array<Int>): Array<Int> =
        (0 until p.size).map { p.indexOf(it + 1) + 1 }
                .map { p.indexOf(it) + 1 }
                .toTypedArray()

    private fun execJumpingOnTheClouds() {
        jumpingOnClouds(arrayOf(0,0,1,0), 2).run {
            if (this != 96) {
                println("Case1 Failure.. your answer = $this . correct answer = 96")
                return
            }
        }
        jumpingOnClouds(arrayOf(0,0,1,0,0,1,1,0), 2).run {
            if (this != 92) {
                println("Case2 Failure.. your answer = $this . correct answer = 92")
                return
            }
        }
        jumpingOnClouds(arrayOf(1,1,1,0,1,1,0,0,0,0), 3).run {
            if (this != 94) {
                println("Case3 Failure.. your answer = $this . correct answer = 94")
                return
            }
        }
    }

    private fun jumpingOnClouds(c: Array<Int>, k: Int): Int {
        var baseStep = c.size / k
        if (c.size % k != 0) {
            baseStep += 1
        }
        val thunderCloudCount = c.filterIndexed {index, i -> index % k == 0 && i == 1}.sum()

        return 100 - (baseStep + (thunderCloudCount * 2))
    }

    private fun execFindDigits() {
        findDigits(12).run {
            if (this != 2) {
                println("Case1 Failure.. your answer = $this correct = 2")
                return
            }
        }
        findDigits(111).run {
            if (this != 3) {
                println("Case2 Failure.. your answer = $this correct = 3")
                return
            }
        }
        findDigits(1012).run {
            if (this != 3) {
                println("Case3 Failure.. your answer = $this correct = 3")
                return
            }
        }
        println("Success!")
    }

    private fun findDigits(n: Int): Int =
         n.toString().map(Character::getNumericValue)
                .filter { it != 0 && n % it == 0 }
                .count()

    private fun exeCutTheSticks() {
        cutTheSticks(arrayOf(1,2,3)).run {
            if (!this.contentEquals(arrayOf(3,2,1))) {
                println("case1 failure.. ${this.forEach(::println)}")
                return
            }
        }
        cutTheSticks(arrayOf(5,4,4,2,2,8)).run {
            if (!this.contentEquals(arrayOf(6,4,2,1))) {
                println("case2 failure.. ${this.forEach(::println)}")
                return
            }
        }
        cutTheSticks(arrayOf(1,2,3,4,3,3,2,1)).run {
            if (!this.contentEquals(arrayOf(8,6,4,1))) {
                println("case3 failure.. ${this.forEach(::println)}")
                return
            }
        }
        println("Success!!!")
    }

    private fun cutTheSticks(arr: Array<Int>): Array<Int> {
        val results = mutableListOf<Int>()
        var localArr = arr.toList()

        while (localArr.isNotEmpty()) {
            results.add(localArr.size)
            val minVal = localArr.min() ?: break
            localArr = localArr.map { it - minVal }.filter { it > 0 }
        }

        return results.toTypedArray()
    }

    private fun execRepeatedString() {
        repeatedString("abcab", 10).run {
            if (this != 4L) {
                println("case1 failure.. $this")
                return
            }
        }
        repeatedString("aba", 10).run {
            if (this != 7L) {
                println("case2 failure.. $this")
                return
            }
        }
        repeatedString("a", 1000000000000).run {
            if (this != 1000000000000L) {
                println("case3 failure.. $this")
                return
            }
        }
        repeatedString("gfcaaaecbg", 547602).run {
            if (this != 164280L) {
                println("case4 failure.. $this")
                return
            }
        }
        println("Success!!")
    }

    private fun repeatedString(s: String, n: Long): Long {

        fun countChar(target: String) = target.count { it == 'a' }

        var result = countChar(s) * (n / s.length)
        if ((n % s.length) != 0L) {
            val remainStr = s.substring(0, (n % s.length).toInt())
            result += countChar(remainStr)
        }
        return result
    }

    private fun execLibraryFine() {
        libraryFine(6, 6 , 2015, 6, 6, 2015).run {
            if (this != 0) {
                println("case1 failure.. $this")
                return
            }
        }
        libraryFine(9, 6 , 2015, 6, 6, 2015).run {
            if (this != 45) {
                println("case2 failure.. $this")
                return
            }
        }
        libraryFine(9, 12, 2017, 4, 7, 2017).run {
            if (this != 2500) {
                println("case3 failure.. $this")
                return
            }
        }
        libraryFine(1, 1, 2018, 12 , 31, 2017).run {
            if (this != 10000) {
                println("case4 failure.. $this")
                return
            }
        }
        libraryFine(3, 4, 3000, 3, 4, 20).run {
            if (this != 29800000) {
                println("case5 failure.. $this")
                return
            }
        }
        libraryFine(1, 12, 2017, 1, 1, 2018).run {
            if (this != 0) {
                println("case6 failure.. $this")
                return
            }
        }
        println("Success!")
    }

    private fun libraryFine(d1: Int, m1: Int, y1: Int, d2: Int, m2: Int, y2: Int): Int {

        fun calcMonth(): Int {

            fun calcDay(): Int =
                when {
                    d1 > d2 -> (d1 - d2) * 15
                    else -> 0
                }

            return when {
                m1 == m2 -> calcDay()
                m1 > m2 -> (m1 - m2) * 500
                else -> 0
            }
        }

        return when {
            y1 == y2 -> calcMonth()
            y1 > y2 -> (y1 - y2) * 10000
            else -> 0

        }
    }

    private fun execEqualizeArray() {
        equalizeArray(arrayOf(1,2,2,3)).run {
            if (this != 2) {
                println("case1 failure.. $this")
                return
            }
        }
        equalizeArray(arrayOf(3,3,2,1,3)).run {
            if (this != 2) {
                println("case2 failure.. $this")
                return
            }
        }
        equalizeArray(arrayOf(3)).run {
            if (this != 0) {
                println("case3 failure.. $this")
                return
            }
        }
        equalizeArray(arrayOf(5,2,3,6654,12,484375843,8,9,0,1,4)).run {
            if (this != 10) {
                println("case4 failure.. $this")
                return
            }
        }
        println("Success!")
    }

    private fun equalizeArray(arr: Array<Int>): Int {

        var mostNumberCount = 0
        var prevNumber: Int? = null
        var numberCount = 0

        arr.sortedArray().forEach { currentNumber ->

            if (prevNumber == null) {
                numberCount = 1
                mostNumberCount = 1
                prevNumber = currentNumber
            } else {
                when (prevNumber) {
                    currentNumber -> numberCount += 1
                    else -> {
                        numberCount = 1
                        prevNumber = currentNumber
                    }
                }
            }
            if (mostNumberCount < numberCount) {
                mostNumberCount = numberCount
            }
        }

        return arr.size - mostNumberCount
    }

    private fun execJumpingOnTheCloud2() {
        jumpingOnClouds2(arrayOf(0,1,0,0,0,1,0)).run {
            if (this != 3) {
                println("Case1 Failure.. your answer = $this . correct answer = 3")
                return
            }
        }
        jumpingOnClouds2(arrayOf(0,0,1,0,0,1,0)).run {
            if (this != 4) {
                println("Case2 Failure.. your answer = $this . correct answer = 4")
                return
            }
        }
        jumpingOnClouds2(arrayOf(0,0,0,0,1,0)).run {
            if (this != 3) {
                println("Case3 Failure.. your answer = $this . correct answer = 3")
                return
            }
        }
        println("Success!")
    }

    private fun jumpingOnClouds2(c: Array<Int>): Int {

        fun isSuperJumpFrom(index: Int) = index + 2 <= c.size - 1 && c[index + 2] == 0

        var takes = 0
        var currentIndex = 0
        while (currentIndex < c.size - 1) {
            currentIndex += if (isSuperJumpFrom(currentIndex)) 2 else 1
            takes += 1
        }
        return takes
    }

    private fun execAcmTeam() {
        arrayOf("10101", "11100", "11010", "00101").run {
            val result = acmTeam(this)
            if (result[0] != 5 || result[1] != 2) {
                println("Case1 Failure.. your answer = ${result[0]} ${result[1]}")
                return
            }
        }
        arrayOf("11101", "10101", "11001", "10111", "10000", "01110").run {
            val result = acmTeam(this)
            if (result[0] != 5 || result[1] != 6) {
                println("Case2 Failure.. your answer = ${result[0]} ${result[1]}")
                return
            }
        }
        println("Success!")
    }
    private fun acmTeam(topic: Array<String>): Array<Int> {

        fun countByOR(s1: String, s2: String): Int =
                (0 until s1.length)
                        .filter { s1[it] == '1' || s2[it] == '1' }
                        .count()

        var knownFieldCount = 0
        var pairCount = 0
        for (outIndex in 0 until topic.size) {
            for (inIndex in outIndex until topic.size) {
                val cnt = countByOR(topic[outIndex], topic[inIndex])
                when {
                    knownFieldCount < cnt -> {
                        knownFieldCount = cnt
                        pairCount = 1
                    }
                    knownFieldCount == cnt -> {
                        pairCount += 1
                    }
                }
            }
        }
        return arrayOf(knownFieldCount, pairCount)
    }

    private fun execTotalPayCost() {
        totalPayCost(10, 10, 1, 1, 1).run {
            if (this != 20) {
                println("Case1 Failure.. your answer = $this")
                return
            }
        }
        totalPayCost(5, 9, 2, 3, 4).run {
            if (this != 37) {
                println("Case2 Failure.. your answer = $this")
                return
            }
        }
        totalPayCost(3, 6, 9, 1, 1).run {
            if (this != 12) {
                println("Case3 Failure.. your answer = $this")
                return
            }
        }
        totalPayCost(7, 7, 4, 2, 1).run {
            if (this != 35) {
                println("Case4 Failure.. your answer = $this")
                return
            }
        }
        totalPayCost(3, 3, 1, 9, 2).run {
            if (this != 12) {
                println("Case5 Failure.. your answer = $this")
                return
            }
        }
        println("Success!")
    }

    private fun totalPayCost(b: Int, w: Int, bc: Int, wc: Int, z: Int): Int {
        val useBCost = if (bc > wc) if (bc > wc + z) wc + z else bc else bc
        val useWCost = if (wc > bc) if (wc > bc + z) bc + z else wc else wc
        return b * useBCost + w * useWCost
    }

    private fun execMinimumDistances() {
        minimumDistances(arrayOf(3,2,1,2,3)).run {
            if (this != 2) {
                println("Case1 Failure.. your answer = $this")
                return
            }
        }
        minimumDistances(arrayOf(7,1,3,4,1,7)).run {
            if (this != 3) {
                println("Case2 Failure.. your answer = $this")
                return
            }
        }
        minimumDistances(arrayOf(1,1,2,2)).run {
            if (this != 1) {
                println("Case3 Failure.. your answer = $this")
                return
            }
        }
        minimumDistances(arrayOf(1,1,1,1)).run {
            if (this != 1) {
                println("Case4 Failure.. your answer = $this")
                return
            }
        }
        minimumDistances(arrayOf(4,7,4,7,7)).run {
            if (this != 1) {
                println("Case5 Failure.. your answer = $this")
                return
            }
        }
        minimumDistances(arrayOf(1,1)).run {
            if (this != 1) {
                println("Case6 Failure.. your answer = $this")
                return
            }
        }
        println("Success!!")
    }

    private fun minimumDistances(a: Array<Int>): Int {
        val alreadyValues = mutableListOf<Int>()
        var distances: MutableList<Int>? = null
        for (index in 0 until a.size - 1) {
            val currentValue = a[index]

            if (alreadyValues.contains(currentValue)) {
                println("すでに $currentValue は走査済みなので除外")
                continue
            }
            val minDistance = calcMinDistance(currentValue, index, a)
            if (minDistance == -1) {
                continue
            }
            if (distances == null) {
                distances = mutableListOf<Int>().apply { add(minDistance) }
            } else {
                distances.add(minDistance)
            }
            alreadyValues.add(currentValue)
        }
        // ツーペアじゃなくていいらしい・・・・・・わからん。
        return distances?.min() ?: -1
    }

    private fun calcMinDistance(value: Int, startIdx: Int, a: Array<Int>): Int {
        var findValueIndex = startIdx
        var currentIdx = startIdx + 1
        var distance = -1
        while (currentIdx < a.size) {
            if (a[currentIdx] == value) {

                val newDistance = currentIdx - findValueIndex
                println("$value と同じ値を発見 distance=$newDistance")
                // 最初に見つけた場合、または以前見つけたindexより距離が近い場合はその値を距離として保持する
                if (distance == -1 || distance > newDistance) {
                    println("  最初に見つけた値または $distance より小さいので再設定")
                    distance = newDistance
                    findValueIndex = currentIdx
                }
            }
            currentIdx += 1
        }
        return distance
    }

    /**
     * 引っかかりどころ
     * なんか規定の実効時間内に終わらずエラーになる。。
     * O(1)でできるようだが・・
     */
    private fun execHalloweenSale() {
        howManyGames(20,3,6,80).run {
            if (this != 6) {
                println("Case1 Failure.. your answer = $this")
                return
            }
        }
        howManyGames(20,3,6,85).run {
            if (this != 7) {
                println("Case2 Failure.. your answer = $this")
                return
            }
        }
        howManyGames(1,1,1,1).run {
            if (this != 1) {
                println("Case3 Failure.. your answer = $this")
                return
            }
        }
        howManyGames(99,3,1,5555).run {
            if (this != 3905) {
                println("Case4 Failure.. your answer = $this")
                return
            }
        }
        println("Success!")
    }

    private fun howManyGames(p: Int, d: Int, m: Int, s: Int): Int {
        if (s < p) {
            return 0
        }
        var count = 0
        var balance = s
        var gamePrice = p
        while (balance >= m) {
            count++
            balance -= gamePrice
            gamePrice = Math.max(gamePrice - d, m)
        }
        return count
    }

    private fun howManyGamesWithOrder1(p: Int, d: Int, m: Int, s: Int): Int {
        if (s < p) {
            return 0
        }
        var n = 1 + (p - m) / d
        val t = n * (2 * p - (n - 1) * d) / 2
        if (s >= t) {
            n += (s - t) / m
        } else {
            val b = 2 * p + d
            n = ((b - Math.sqrt((b * b - 8 * d * s).toDouble())) / (2 * d)).toInt()
        }
        return n
    }
}