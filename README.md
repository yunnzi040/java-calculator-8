# Java Calculator Precourse

## 구현 기능 목록

각 기능은 개별 커밋으로 구현됩니다.

### 1. 기본 입력 처리

- 사용자로부터 문자열 입력 받기
- null 또는 빈 문자열("") 입력 시 0 반환
- 입력 문자열의 앞뒤 공백 제거(trim)

### 2. 커스텀 구분자 처리

- 입력 문자열이 //로 시작하면 커스텀 구분자로 판단
- "//"와 "\n" 사이의 문자를 커스텀 구분자로 추출
- 커스텀 구분자 지정 시 "\n" 이후 문자열을 본문으로 인식
- 커스텀 구분자를 기준으로 숫자 분리

### 3. 기본 구분자 처리

- 기본 구분자(쉼표 , 또는 콜론 :)로 숫자 분리
- 분리된 문자열을 정수로 변환
- 변환된 숫자들의 합계 계산

### 4. 예외 처리

- 구분자로 분리된 값이 비어 있으면 IllegalArgumentException 발생
- 숫자가 아닌 문자가 포함된 경우 IllegalArgumentException 발생
- 음수가 포함된 경우 IllegalArgumentException 발생
- 커스텀 구분자를 지정하지 않았는데 기본 구분자 외의 문자가 포함된 경우 IllegalArgumentException 발생
- 커스텀 구분자를 지정했는데 입력 문자열에 기본 구분자가 함께 포함된 경우 IllegalArgumentException 발생

### 5. 출력 기능

- 계산된 합계를 콘솔에 출력

## 테스트 계획

각 단계별로 다음 테스트 케이스를 작성합니다.

### 1. 기본 입력 처리 테스트
- `null` 입력 → 0 반환
- 빈 문자열 `""` 입력 → 0 반환
- 공백이 포함된 입력 `" 1,2,3 "` → 6 반환

### 2. 커스텀 구분자 처리 테스트
- `"//;\n1;2;3"` → 6 반환
- `"//|\n1|2|3"` → 6 반환
- `"//;\n1,2,3"` → IllegalArgumentException 발생

### 3. 기본 구분자 처리 테스트
- `"1,2,3"` → 6 반환
- `"1:2:3"` → 6 반환
- `"1,2:3"` → IllegalArgumentException 발생

### 4. 예외 처리 테스트
- 빈 값: `"1,,3"` → IllegalArgumentException 발생
- 숫자 아님: `"1,a,3"` → IllegalArgumentException 발생
- 음수: `"1,-2,3"` → IllegalArgumentException 발생
- 구분자 혼용: `"//;\n1,2;3"` → IllegalArgumentException 발생

### 5. 출력 기능 테스트
- 계산 결과가 콘솔에 올바르게 출력되는지 확인