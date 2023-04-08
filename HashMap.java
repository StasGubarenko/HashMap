import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {


    private int defaultSize = 16;


    private int size;

    private Node<K, V>[] table;


    public MyHashMap() {
        table = new Node[defaultSize];
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static int convertHashToIndex(Object key) {

        int hashKey = hash(key);
        return getIndex(hashKey);

    }


    public V put(K key, V value) {

        int index = convertHashToIndex(key);

        Node<K, V> temp = table[index];
        Node<K, V> temp2 = new Node<>(key, value);
        //Если по определенному индексу нода отсутствует, то добавляем ноду
        if (temp == null) {
            table[index] = new Node<>(key, value);
            size++;
            return value;
        }
        //Если по индексу есть ноды, то сравниваем ключи двух нод. Если ключи равны, то меняем value
        else if (key.equals(temp.key)) {
            table[index].value = temp2.value;
            return value;
        }

        //Проверяем содержит действующая нода ссылку на следующую ноду
        else if (temp.next == null) {

            temp.next = temp2;
            temp2.next = null;
            size++;
            return temp2.value;
        }
        //Если нода по индексу содержит более 1 ноды, то итерируемся до последней ноды
        else if (temp.next != null) {
            while (temp.next != null) {
                temp = temp.next;
            }

            //Устанавливаем ссылку на добавленную ноду
            temp.next = temp2;
        }
        size++;
        return temp2.value;
    }

    public V get(Object key) {

        if (size() == 0) {
            return null;
        }

        int index = convertHashToIndex(key);

        Node<K, V> temp = table[index];


        if (temp == null) {
            return null;
        }

        if (temp.key.equals(key)) {
            return temp.value;
        }

        if (table[index] != null) {

            while (temp.next != null) {
                if (temp.getKey().equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }

            if (temp.getKey().equals(key)) {
                return temp.value;
            }
        }

        return null;

    }

    public V remove(Object key) {

        if (size() == 0) {
            return null;
        }

        int index = convertHashToIndex(key);

        Node<K, V> previous = null;

        Node<K, V> node = table[index];

        if (node.key.equals(key) && size() <= defaultSize) {
            V valueByKey = node.value;
            table[index] = null;
            size--;
            return valueByKey;
        }

        do {
            if (node.key.equals(key)) {
                if (previous == null) {
                    previous = node.next;
                    table[index] = previous;
                    size--;
                    return node.value;
                }
            } else {
                previous = node;
                node = node.next;
                if (node.key.equals(key)) {
                    previous.next = node.next;
                    size--;
                    return node.value;
                }
            }
        } while (node.next != null);

        return null;


    }

    public boolean containsKey(Object key) {

        if (size() == 0) {
            return false;
        }

        int index = convertHashToIndex(key);

        Node<K, V> temp = table[index];

        if (table[index] == null) {
            return false;
        }

        if (table[index] != null) {

            do {
                if (temp.getKey().equals(key)) {
                    return true;
                }
                temp = temp.next;
            } while (temp.next != null);
        }


        if (temp.getKey().equals(key)) {
            return true;
        }
        return false;
    }

    public static int hash(Object key) {

        int h;

        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
        }

        if (h >= 0) {
            return h;
        }

        return ~h;
    }

    //функция, которая преобразовывает хеш в индекс
    public static int getIndex(int hash) {
        return hash % 16;
    }


    private static class Node<K, V> {

        private K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        //Переписал equals
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof Node) {
                Node<K, V> obj = (Node<K, V>) o;
                return Objects.equals(key, obj.getKey()) && Objects.equals(value, obj.getValue());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }


    @Override
    public String toString() {

        return "MyHashMap1{" + ", table=" + Arrays.toString(table) + '}';
    }


}

