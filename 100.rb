
def max_cycle(a, b)
  (a..b).map {|i| cycle(i)}.max
end

def cycle(n)
  k = n 
  l = 1
  until k == 1
    if k % 2 == 0
      k /= 2
    else
      k = 3 * k + 1
    end
    l += 1
  end
  l
end

until ((line = gets) == nil)
  line =~ /(\d+) (\d+)/
  a, b = $1.to_i, $2.to_i
  c = max_cycle(a, b)
  puts "#{a} #{b} #{c}"
end


