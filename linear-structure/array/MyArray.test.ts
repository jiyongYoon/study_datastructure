// MyArray.test.ts
import { MyArray } from './MyArray';

// test 묶어주는 역할
describe('MyArray', () => {
    let myArray: MyArray;

    beforeEach(() => {
        const intArray = new Int32Array(5);
        myArray = new MyArray(intArray);
    });

    test('should assign and get values correctly', () => {
        myArray.assign(0, 100);
        myArray.assign(1, 200);
        myArray.assign(4, 500);

        expect(myArray.get(0)).toBe(100);
        expect(myArray.get(1)).toBe(200);
        expect(myArray.get(2)).toBe(0);   // 초기값
        expect(myArray.get(4)).toBe(500);
    });

    test('should throw error when getting out-of-bounds', () => {
        expect(() => myArray.get(-1)).toThrow('Index -1 is out of bounds');
        expect(() => myArray.get(5)).toThrow('Index 5 is out of bounds');
    });

    test('should throw error when assigning out-of-bounds', () => {
        expect(() => myArray.assign(-1, 123)).toThrow('Index -1 is out of bounds');
        expect(() => myArray.assign(5, 456)).toThrow('Index 5 is out of bounds');
    });
});
