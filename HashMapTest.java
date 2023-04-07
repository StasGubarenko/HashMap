
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;


class MyHashMapTest {
    @Test
    @DisplayName("Проверка добавление записи в мапу")
    public void testAddingNodeInHashMap() {
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();

        int valueOfHashMap = 10;
        for(int i = 0; i < 1000; i++){
            hashMap.put(i,valueOfHashMap);
            valueOfHashMap += 10;
        }

        int valueOfArray = 10;
        int[] arrayOfValue = new int[1000];
        for(int i = 0; i < 1000; i++){
            arrayOfValue[i] = valueOfArray;
            valueOfArray += 10;
        }

        Assertions.assertAll(() ->{
            for(int i = 0; i < arrayOfValue.length; i++){
                if(arrayOfValue[i] != hashMap.get(i)){
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка перезаписи value при одинаковых ключах")
    public void replaceValueWhenKeysIsTheSame()  {
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        hashMap.put(10, 40);
        Assertions.assertEquals(40,hashMap.get(10));
        hashMap.put(10, 99);
        Assertions.assertEquals(99,hashMap.get(10));

        Assertions.assertEquals(1,hashMap.size());
    }

    @Test
    @DisplayName("Проверка добавления null key")
    public void testAddingNullKey(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Assertions.assertDoesNotThrow(()-> hashMap.put(null, 10));
        Assertions.assertEquals(1,hashMap.size());

    }

    @Test
    @DisplayName("Проверка добавления null value")
    public void testAddingNullValue(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Assertions.assertDoesNotThrow(()-> hashMap.put(1, null));
        Assertions.assertEquals(1,hashMap.size());

    }

    @Test
    @DisplayName("Проверка возврата true при пустой мапе")
    public void testReturningTrueWhenHashMapIsEmpty(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Assertions.assertTrue(hashMap.isEmpty());
    }

    @Test
    @DisplayName("Проверка возврата false при заполненной мапе")
    public void testReturningFalseWhenHashMapIsEmpty(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        hashMap.put(1,1);
        Assertions.assertFalse(hashMap.isEmpty());
    }

    @Test
    @DisplayName("Проверка получения value по ключу")
    public void testReturningValueByKey(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        hashMap.put(1,10);
        Assertions.assertEquals(10,hashMap.get(1));
    }

    @Test
    @DisplayName("Проверка возврата false при несуществующем ключе")
    public void returnFalseWhenKeyDoesNotExist(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        hashMap.put(1,10);
        Assertions.assertFalse(hashMap.containsKey(10));
    }

    @Test
    @DisplayName("Проверка возврата true при существующем ключе")
    public void returnTrueWhenKeyDoesNotExist(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        hashMap.put(1,10);
        Assertions.assertTrue(hashMap.containsKey(1));
    }

    @Test
    @DisplayName("Проверка наличия ключа в мапе")
    public void сheckKeyInTheHashMap() {
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();

        int valueOfHashMap = 10;
        for(int i = 0; i < 1000; i++){
            hashMap.put(i,valueOfHashMap);
            valueOfHashMap += 10;
        }


        Assertions.assertAll(() ->{
            for(int i = 0; i < 1000; i++){
                if(!hashMap.containsKey(i)){
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка метода get при пустой мапе")
    public void testTheMethodGetWhenHashMapIsEmpty(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Assertions.assertNull(hashMap.get(0));
    }

    @Test
    @DisplayName("Проверка метода containsKey при пустой мапе")
    public void testContainsKeyWhenHashMapIsEmpty(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Assertions.assertFalse(hashMap.containsKey(0));
    }

    @Test
    @DisplayName("Проверка метода containsKey при коллизии")
    public void testContainsKeyWhenThereIsCollision(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();

        int valueOfHashMap = 10;
        for(int i = 0; i < 100; i++){
            hashMap.put(i,valueOfHashMap);
            valueOfHashMap += 10;
        }

        Integer keyInHashMap = 0;
        for(int i = 0; i < 7; i++){
            Assertions.assertTrue(hashMap.containsKey(keyInHashMap));
            keyInHashMap += 16;
        }
    }

    @Test
    @DisplayName("Проверка метода containsKey при отсутствии коллизии")
    public void testContainsKeyWhenThereIsNotCollision(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();

        int valueOfHashMap = 10;
        for(int i = 0; i < hashMap.getDefaultSize(); i++){
            hashMap.put(i,valueOfHashMap);
            valueOfHashMap += 10;
        }

        Integer[] arraysOfKeys = new Integer[hashMap.getDefaultSize()];
        for(int i = 0; i < hashMap.getDefaultSize(); i++){
            arraysOfKeys[i] = i;
        }

        for(int i = 0; i < hashMap.getDefaultSize(); i++){
            Assertions.assertTrue(hashMap.containsKey(arraysOfKeys[i]));
        }
    }

    @Test
    @DisplayName("Проверка remove() при пустой мапе")
    public void removeWhenHashMapIsEmpty(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Assertions.assertNull(hashMap.remove(33));
    }

    @Test
    @DisplayName("Проверка remove() при размере мапы 16(нет коллизии)")
    public void removeWhenHashMapHasSize16(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Integer value = 10;

        for(int i = 0; i < hashMap.getDefaultSize();i++){
            hashMap.put(i,value);
            value += 10;
        }

        Integer[] arraysOfKeys = new Integer[hashMap.getDefaultSize()];

        Integer value2 = 10;
        for(int i = 0; i < hashMap.getDefaultSize(); i++){
            arraysOfKeys[i] = value2;
            value2 += 10;
        }

        arraysOfKeys[2] = null;

        Assertions.assertEquals(30,hashMap.remove(2));

        Assertions.assertAll(() ->{
            for(int i = 0; i < arraysOfKeys.length; i++){
                if(!Objects.equals(arraysOfKeys[i], hashMap.get(i))){
                    throw new RuntimeException();
                }
            }
        });

        Assertions.assertEquals(15,hashMap.size());
    }

    @Test
    @DisplayName("Проверка удаления ноды из массива при наличии коллизии")
    public void removeNodeFromArrayWhenThereIsCollision(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Integer value = 10;

        for(int i = 0; i < 100;i++){
            hashMap.put(i,value);
            value += 10;
        }

        Integer[] arraysOfKeys = new Integer[100];

        Integer value2 = 10;
        for(int i = 0; i < 100; i++){
            arraysOfKeys[i] = value2;
            value2 += 10;
        }
        arraysOfKeys[0] = 170;

        Assertions.assertEquals(10,hashMap.remove(0));
        Assertions.assertEquals(99,hashMap.size());

        hashMap.remove(0);

    }

    //CСпросить как проверить
    @Test
    @DisplayName("Удаление ноды из связанного списка в мапе")
    public void removeNodeInLinkedListInHashMap(){
        MyHashMap<Integer, Integer> hashMap = new MyHashMap<>();
        Integer value = 10;

        for(int i = 0; i < 100;i++){
            hashMap.put(i,value);
            value += 10;
        }

        Integer[] arraysOfKeys = new Integer[100];

        Integer value2 = 10;
        for(int i = 0; i < 100; i++){
            arraysOfKeys[i] = value2;
            value2 += 10;
        }


        Assertions.assertEquals(650,hashMap.remove(64));
        Assertions.assertEquals(99,hashMap.size());

    }


}
