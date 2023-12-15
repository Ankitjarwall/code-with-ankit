def get_sum(n):
  if n < 0:
    raise ValueError("n must be non-negative")
  if n == 0:
    return 0
  r=n%10
  n=n//10
  return r +sum(n)


n = int(input("Enter the number: "))
sum = get_sum(n)
print(sum)
