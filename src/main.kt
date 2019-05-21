fun is_space(c: Char): Int
{
    if ((c == ' ') || (c == '\t') || (c == '\n'))
        return 0
    else
        return 1
}

fun findMarkerIgnoringSpace(text: String, marker: String): TextRange?
{
    var textRange = TextRange(0, 0)
    var i_text: Int = 0
    var j_marker: Int = 0
    var start: Int = 0
    var len_t: Int = text.length
    var len_m: Int = marker.length
    var forgot_space: Int = 0

    while ((i_text < len_t) &&  j_marker < len_m)
    {
        if (is_space(marker[j_marker]) == 0 && is_space(text[i_text]) == 1)
        {
            j_marker += 1
            forgot_space += 1
            continue
        }
        if (text[i_text] == marker[j_marker])
        {
            j_marker +=1
        }
        i_text +=1
    }
    println("${i_text} ${len_t}")
    println("${j_marker} ${len_m}")
    if (j_marker != len_m && j_marker == 0)
        return null
    return TextRange(start, i_text + forgot_space)
}

fun main(Argc: Array<String>)
{
   println(findMarkerIgnoringSpace("Text [1299bba / 0 0 0 0 1] from David", "T"))
}