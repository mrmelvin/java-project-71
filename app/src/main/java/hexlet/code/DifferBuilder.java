package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

public class DifferBuilder {

    /**
     * Статический метод сравнивающий два Map и возвращающий в виде отображения Map<String, Object[]>.
     * @param firstData Данные, переданные от первого файла
     * @param secondData Данные, переданные от второго файла
     * @return result Отображение. Представляет собой отображание вида Map<String, Object[]>, где
     *                  String - ключ параметров из двух файлов;
     *                  Object[] - массив из 4 параметров:
     *                      первый параметр отвечает за наличие ключа в первом файле (0 - отсутствие, 1 - наличие),
     *                      второй параметр отвечает за наличие ключа во втором файле (0 - отсутствие, 1 - наличие),
     *                      третий параметр - значение ключа в первом файле (если значения нет выставляется null),
     *                      четвёртый параметр - значение ключа во втором файле (если значения нет выставляется null).
     *                 Возможный вид Map<"key2", [0, 1, null, "foo"]> -
     *                                                              во втором файле добавилось значение {"key2": "foo"}
     *
     * @throws IOException
     */
    public static Map<String, Object[]> differBuilder(Map firstData, Map secondData) throws IOException {
        Map<String, Object[]> result = new LinkedHashMap<>();

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(firstData.keySet());
        allKeys.addAll(secondData.keySet());
        for (String elem: allKeys) {
            if (firstData.containsKey(elem) & secondData.containsKey(elem)) {
                result.put(elem, new Object[]{1, 1, firstData.getOrDefault(elem, null),
                        secondData.getOrDefault(elem, null)});
            } else if (firstData.containsKey(elem) & !secondData.containsKey(elem)) {
                result.put(elem, new Object[]{1, 0, firstData.getOrDefault(elem, null),
                        secondData.getOrDefault(elem, null)});
            } else {
                result.put(elem, new Object[]{0, 1, firstData.getOrDefault(elem, null),
                        secondData.getOrDefault(elem, null)});
            }
        }
        return result;
    }
}
