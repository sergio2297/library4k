package es.sfernandez.libraries.library4k

class LoremIpsum(
    private val generationType: GenerationType = GenerationType.PARAGRAPH,
    private val numOfGeneration: Int = 3,
) {

    //---- Constants and Definitions ----
    enum class GenerationType {
        WORDS, SENTENCES, PARAGRAPH
    }

    private val loremIpsumParagraph = "Voluptua sed vero ad no sanctus aliquyam stet et. Et iusto diam eum aliquyam clita diam nostrud. " +
                "Vulputate justo sed at no wisi invidunt duis justo stet diam sed lorem magna justo lorem. Clita " +
                "in ipsum dolor magna dolor. Sed amet vero ipsum vero invidunt hendrerit vel invidunt magna duo " +
                "vero duis ipsum blandit ipsum vero. Dolore dolore et tempor nibh at et id erat tation. Takimata " +
                "sed eos dolores et duo. Sed dolore labore dolore. Est vero dolor dolor doming sit ea. Hendrerit " +
                "erat labore sit dolor clita lorem placerat. Diam labore euismod magna et sit ipsum nibh no ea " +
                "sit suscipit takimata et vero nibh stet. Stet takimata erat hendrerit. Aliquyam vero erat kasd " +
                "sadipscing lobortis illum et rebum tempor eirmod duis sit. Velit et sed lorem praesent consectetuer " +
                "ea rebum sed consectetuer ipsum elitr no."

    //---- Constructor ----
    init {
        checkIfNumOfGenerationIsPositive()
    }

    private fun checkIfNumOfGenerationIsPositive() {
        if(numOfGeneration < 0)
            throw IllegalArgumentException("Error. Num of elements to generate must not be negative.")
    }

    //---- Methods ----
    fun generate() : String {
        return extractLoremIpsumFromSeed()
            .reduce { s1, s2 -> "$s1\n$s2" }
    }

    fun generateHtml() : String {
        return extractLoremIpsumFromSeed()
            .reduce { s1, s2 -> "$s1<p>$s2</p>" }
    }

    private fun extractLoremIpsumFromSeed() : Sequence<String> {
        return when(generationType) {
            GenerationType.WORDS -> generateWords()
            GenerationType.SENTENCES -> generateSentences()
            GenerationType.PARAGRAPH -> generateParagraphs()
        }
    }

    private fun generateWords(): Sequence<String> {
        return loremIpsumParagraph.splitToSequence(Regex("\\w"), numOfGeneration)
    }

    private fun generateSentences(): Sequence<String> {
        return loremIpsumParagraph.splitToSequence(Regex("\\."), numOfGeneration)
    }

    private fun generateParagraphs(): Sequence<String> {
        return sequenceWith(numOfGeneration) { loremIpsumParagraph }
    }

}