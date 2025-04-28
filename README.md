## Jest

- 페이스북에서 개발한 자바스크립트 테스트 프레임워크

### 설치

```
npm install --save-dev jest ts-jest @types/jest
```

### Jest 설정파일 만들기
```
npx ts-jest config:init
```

### 실행
```
npx jest
```
- 파일 이름이 `*.test.ts`나 `*.spec.ts`로 끝나면 자동으로 찾아서 실행해줌

```
npx jest <파일명 혹은 경로>
```
- 특정 테스트만 실행