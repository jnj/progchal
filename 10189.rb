
def neighbors(col, row)
  res = []
  a = [-1, 0, 1]
  a.each do |i|
    a.each do |j|
      res << [col + i, row + j]
    end
  end
  res
end

def sums(field, width, height)
  newfield = {}
  (0...height).each do |row|
    (0...width).each do |col|
      if field[[col,row]] == '*'
        newfield[[col,row]] = '*'
        next
      end
      sum = neighbors(col, row).inject(0) {|s, n| s + (field[n] == '*' ? 1 : 0)}
      newfield[[col,row]] = sum
    end
  end
  newfield
end

def render(field, fieldnum, width, height)
  puts "Field #{fieldnum}:"
  (0...height).each do |row|
    (0...width).each {|col| print field[[col, row]]}
    puts
  end
  puts
end

fieldnum = 1
while true
  field = {}
  height, width = gets.split.map {|s| s.to_i}
  exit(0) if height < 1 or width < 1

  (0...height).each do |row|
    line = gets.strip
    line.split(//).each_with_index do |b, col|
      field[[col, row]] = b
    end
  end
  
  render(sums(field, width, height), fieldnum, width, height)
  fieldnum += 1
end
