function init()	--do initial calculations (primes, totient, ect)
	local p, q = 9431, 8837
	n = p*q
	local totient = (p - 1) * (q - 1)
	local e = 17
	local d = (1 + 11 * totient) / e
	pub_key = e
	prv_key = d
end

function getKey()		--gets the public keypair
	return n, pub_key	--multiple return values
end

function mod_exp(b, exp, modulus)			--modular exponentiation allows us to calculate
	if modulus == 1 then return 0 end		--this without actually b^exp
	print("calculating " .. b .. ' to the power of ' .. exp .. ' mod ' .. modulus)
	local c = 1
	for i=1, exp do
		c = (c * b) % modulus
	end
	return c
end

function encrypt(message, pub, modulus)
	return mod_exp(message, pub, modulus)
end

function decrypt(message, modulus)
	return mod_exp(message, prv_key, modulus)
end

function stringToNum(s) --takes a string
	local nums, i = {}, 1
	s = string.lower(s)

	repeat
		if string.len(s) > 5 then
			nums[i] = string.sub(s, 1, 5)
			s = string.sub(s, 6)
			i = i + 1
		else
			nums[i] = string.sub(s, 1)
			s = ""
		end
	until string.len(s) == 0

	for j = 1, i do
		local str, num = nums[j], 0
		for k = 1, string.len(str) do
			num = num * 28
			local strb = string.byte(string.sub(str, k, k))
			if strb == string.byte(" ")
			then strb = 1
			else
				strb  = strb - 95
			end
			num = num + strb
		end
		nums[j] = num
	end
	return nums
end

function numToString(n) --takes a table of numbers
	local str = ""

	for i, j in pairs(n) do
		local num = j;
		local strbld = ""
		while num ~= 0 do
			local char = num % 28
			if char == 1 then
				strbld = " " .. strbld
			else
				strbld = string.char(char + 95) .. strbld
			end
			num = math.floor(num / 28)
		end
		str = str .. strbld
	end
	return str
end

init()
io.write("Public keypair: ")
print(getKey())								--print keypair

repeat
	test_string = io.read()					--read in string to be encoded
	io.flush()
	print ('read in: ' .. test_string)
	tab = stringToNum(test_string)			--convert string to table of numbers

	for i, j in pairs(tab) do
		tab[i] = encrypt(j, pub_key, n)		--RSA encrypt each number in the table
	end

	encoded = numToString(tab)

	io.write('Encoded string as a number: ')
	for i, j in pairs(tab) do
		io.write(tab[i])					--print the table as one long number
	end

	print()
	print("encoded string: " .. encoded)	--print the table as one long string

	for i, j in pairs(tab) do
		tab[i] = decrypt(j, n)				--TSA decrypt each number in the table
	end

	res = numToString(tab)					--convert the table of numbers back to strings

	print("decoded result: " .. res)		--print the (hopefully) original string
until test_string == ""
