package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class DifferBuilder {

    /**
     * Статический метод сравнивающий два Map и возвращающий в виде отображения Map<String, Object[]>.
     * @param firstData Данные, переданные от первого файла
     * @param secondData Данные, переданные от второго файла
     * @return result Отображение. Представляет собой отображание вида Map<String, Map<String, Object[]>>, где
     *                  String - ключ параметров из двух файлов;
     *                  Map<String, Object[]> - представление, включающее в себя:
     *                      String - тип данных (добавлен - added,
     *                                          удален - deleted,
     *                                          изменен - changed,
     *                                          без изменений - noChanged)
     *                      Object[] - массив, содержащий сами данные, всегда содержит один элемент, за исключением
     *                      если данные были изменены, тогда содержит старое и новое значение соответственно
     *
     * @throws IOException
     */
    public static Map<String, Map<String, Object[]>> differBuilder(Map firstData, Map secondData) throws IOException {
        Map<String, Map<String, Object[]>> result = new LinkedHashMap<>();

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(firstData.keySet());
        allKeys.addAll(secondData.keySet());
        for (String elem: allKeys) {
            if (firstData.containsKey(elem) & secondData.containsKey(elem)) {
                if (Objects.equals(firstData.getOrDefault(elem, null), secondData.getOrDefault(elem, null))) {
                    result.put(elem, Map.of("noChanged", new Object[]{firstData.getOrDefault(elem, null)}));
                } else {
                    result.put(elem, Map.of("changed", new Object[]{firstData.getOrDefault(elem, null),
                                                                    secondData.getOrDefault(elem, null)}));
                }
            } else if (firstData.containsKey(elem) & !secondData.containsKey(elem)) {
                result.put(elem, Map.of("deleted", new Object[]{firstData.getOrDefault(elem, null)}));
            } else {
                result.put(elem, Map.of("added", new Object[]{secondData.getOrDefault(elem, null)}));
            }

        }
        return result;
    }
}
