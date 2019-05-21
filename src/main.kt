fun is_space(c: Char): Int
{
    if ((c == ' ') || (c == '\t') || (c == '\n'))
        return 1
    else
        return 0
}

fun only_space(text: String): Int
{
    var str_len: Int = text.length - 1

    while ((text[str_len] == ' ' || text[str_len] == '\t' || text[str_len] == '\n') && str_len >= 0)
    {
        str_len -= 1
        if (str_len < 0)
            return (0)
    }
    return (1)
}

fun findMarkerIgnoringSpace(text: String, marker: String): TextRange?
{
    var end_r: Int
    var len_text: Int  // использую для длины строки text (нумерация с 0)
    var len_mark: Int  // использую для длины строки marker (нумерация с 0)
    var len_text2: Int // использую для повторной итерации
    var forgot_space: Int

    end_r = 0
    len_text = text.length - 1
    len_text2 = text.length - 1
    len_mark = marker.length - 1
    forgot_space = 0

    if  (len_text < len_mark || text.length == 0 || marker.length == 0) //проверка на пустоту и не соответствние размера
        return null
    if (only_space(text) == 0 || only_space(marker) == 0) //проверяем не состоит ли файл из пробелов
        return null
    while (len_text >= 0 && len_mark >=0) //запуск анализа строк
    {

        while (is_space(text[len_text]) == 1 && is_space(marker[len_mark]) == 0) // проверка случайно поставленных пробелов
            len_text--
        while (is_space(marker[len_mark]) == 1 && is_space(text[len_text]) == 0) // проверка пробелов которые забыли поставить (считаем забытые пробелы)
        {
            len_mark -= 1
            forgot_space += 1
        }
        if (text[len_text] == marker[len_mark] && is_space(text[len_text]) == 0 && (is_space(marker[len_mark]) == 0)) //нашли первое совпадение (не пробел)
        {
            len_text2 = len_text
            end_r = len_text2                                       // заминаем позицию первого совпадения
            if (len_mark == 0)
                return (TextRange(len_text2, end_r + forgot_space)) // если один симвл в строке маркер marker выходим
            len_text2 -= 1                                          // если нет то двигаемся дальше
            len_mark -= 1
            while (len_text2 >= 0 && len_mark >=0)                  //цикл для проверки целостности вхождения
            {
                while (is_space(text[len_text2]) == 1)               //цпроверка случайно поставленных пробелов
                    len_text2--
                while (is_space(marker[len_mark]) == 1)              //проверка пробелов которые забыли поставить (считаем забытые пробелы)
                {
                    len_mark -= 1
                    forgot_space += 1
                }
                if (text[len_text2] == marker[len_mark])             //найденное совпадение, ищем дальше
                {
                    if (len_mark == 0)                               //если маркер уже кончился выводим результат (считаем что он найден весь)
                        return (TextRange(len_text2, end_r + forgot_space))
                    len_text2 -= 1
                    len_mark -= 1
                }
                if (text[len_text2] != marker[len_mark] && is_space(text[len_text2]) == 0 && is_space(marker[len_mark]) == 0)   // если порядок символов нарушен выходим из цикла
                    break
            }
        }
    len_text -= 1
    end_r = 0                                                         //подготовка к повторной итерации
    len_mark = marker.length - 1
    forgot_space = 0
    }
    return (null)
}

fun main(Argc: Array<String>)
{
   println(findMarkerIgnoringSpace("Text [1299bba / 0 0 0 0 1] from David", "[1299 bba/00001]"))
}