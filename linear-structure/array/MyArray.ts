export class MyArray {
    private arr: Int32Array;
    private length: number;

    constructor(arr: Int32Array) {
        this.length = arr.length;
        this.arr = arr;
    }

    public get(index: number) {
        if (index < 0 || index > this.length - 1) {
            throw new Error(`Index ${index} is out of bounds`);
        }

        return this.arr[index];
    }

    public assign(index: number, value: number) {
        if (index < 0 || index > this.length - 1) {
            throw new Error(`Index ${index} is out of bounds`);
        }

        this.arr[index] = value;
    }

}