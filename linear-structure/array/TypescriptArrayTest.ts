import { MyArray } from "./MyArray";

// 1. Int32Array를 만들어서 MyArray에 넘긴다
const intArray = new Int32Array(5);  // 길이 5짜리 배열 (모든 값은 기본 0)
const myArray = new MyArray(intArray);

// 2. 값 할당(assign) 테스트
myArray.assign(0, 10);
myArray.assign(1, 20);
myArray.assign(4, 50);

// 3. 값 읽기(get) 테스트
console.log(myArray.get(0)); // 10
console.log(myArray.get(1)); // 20
console.log(myArray.get(2)); // 0 (초기값)
console.log(myArray.get(4)); // 50
