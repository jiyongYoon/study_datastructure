# 큐 (Queue)

---

# 1. 이론 학습

- 선입선출(First In First Out; FIFO) 자료구조
- 입력 순서대로 데이터 처리
- Use Case
  - 프린터 출력 대기열, 넓이우선탐색(Breath-First Search; BFS) 등

## 기본 구조

- 큐 공간이 있으며, 앞 - 뒤가 구분되어 있음
- 데이터 추가(Enqueue)는 뒤에서 이루어지며, 데이터 꺼내기(DeQueue)는 앞에서 이루어짐 

## 장점

- 데이터 추가/삭제 시 O(1)로 빠름

## 단점

- 중간 데이터 접근이 어려움

---

# 2. 구현 실습

## 구현된 MyArrQueue (Array 기반)
[MyArrQueue.java](MyArrQueue.java)
## 구현된 MyQueue (LinkedList 기반)
[MyQueue.java](MyQueue.java)

---

# 3. 구현하면서 느낀 점

### Array와 LinkedList의 장단점 파악

Queue의 기본 자료구조도 결국 배열 혹은 리스트이다. 그래서 이 기본 특징을 생각하며 적합한 자료구조를 선택할 수 있다.

Java에서 제공하는 `Queue` 인터페이스 구현체 중 가장 대중적인 Queue는 `ArrayDeque`와 `LinkedList` 이다.

|     **항목**      |   **구현 방식**   |          **장점**          |         **단점**         |
|:---------------:|:-------------:|:------------------------:|:----------------------:|
| **ArrayDeque**  |    원형 배열 큐    | 성능 우수(메모리 및 CPU 캐시 친화적)  | 크기 제한이 있으며 리스트 구현체가 아님 |
| **LinkedList**  |   연결 리스트 기반   |  크기 제한이 없고 리스트 추가기능 존재   |  캐시 지역성 떨어져 성능 약간 낮음   |

ArrayDeque 또한 Collection 인터페이스를 구현했기 때문에 순회 등은 가능하다. 따라서 기본 선택지는 `ArrayDeque`가 되며, 
멀티스레드 환경에서 블로킹 기능이 필요한 경우는 `ArrayBlockingQueue`를,
우선순위에 따라 정렬이 필요한 경우는 나중에 학습할 힙 기반의 `PriorityQueue`를 사용한다. 

---

# 4. 추가적인 도전과제

없음