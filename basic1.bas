;数字当てゲーム
;指定された範囲の間に数字を当ててみる
;正しかったらレベルアップで範囲が広くなる
;正しくなければHPが減って、ゼロになったらゲームオーバー
;最後にハイスコアによってのランキング記録・表示も

;初期設定
cheats = false ;答えを教えてくれる（デバグに便利）
level = 1
score = 0
hpMax = 6
ended = false
rangeStep = 50
highscoreFile = "highscores.txt"
highscoreListMax = 10

;最初のレベルの設定
hp = hpMax
range = level * rangeStep
rand = rand(range-1)+1
if cheats = true then print "Answer: " + rand
print "Level: " + toStr(level)

;くだらないeaster eggのため（文字を逆に出力）
perfect = 0
perfectThreshold = 3

;ゲームループ
do
	;ターンスタート
	print "HP: " + hp
	
	;入力を求める+チェック
	do
		;入力を求める
		str = "1~" + toStr(range) + "の数字を当ててみてください： "
		if perfect >= perfectThreshold then print -str else print str ;連続perfectでくだらないeaster egg(この文字が負にされることで逆になる)
		guess = toInt(read())
		
		;入力をチェック
		ok = false
		if guess > 0 then
			if guess <= range then
			ok = true endif
		endif
		
	loop until ok = true
	
	;当たってたら
	if guess = rand then
		if hp = hpMax then perfect = perfect + 1 else perfect = 0 ;perfectじゃないならperfectのリセット
	
		print "正解！"
		
		;スコアと次のレベルの設定
		score = score + (hp * range)
		print "Score :" + toStr(score) + "\n"
		
		level = level + 1
		print "Level: " + toStr(level)
		
		range = level * rangeStep
		
		hp = hpMax
		rand = rand(range-1)+1
		
		if cheats = true then print "Answer: " + rand
		
	;当たってなかったら
	else
		hp = hp-1
		
		;もうHPないなら
		if hp <= 0 then
		ended = true elseif guess > rand then
		;大きいなら
		print "大きすぎる" else
		;小さいなら
		print "小さすぎる" endif
	endif
	
;ゲームループの終わり
loop until ended = true

;結果表示
print "Game Over"
print "Answer: " + rand
print "Score: " + toStr(score)


;ハイスコア
;配列がないので、hashMapのfunctionで提供されてるmapPut, mapGetを利用して工夫してる

;読み込み
if openFileRead(highscoreFile) = true then
	for i = 1 to highscoreListMax
		name = readFile()
		if name = null then name = " " ;ファイルがおかしいなら取得したデータを扱えるデータ型に変換
		highscore = readFile()
		if highscore = null then highscore = 0 ;ファイルがおかしいなら取得したデータを扱えるデータ型に変換
		mapPut("name" + i, name)
	mapPut("score" + i, highscore) next i
closeFileRead() else
	for i = 1 to highscoreListMax
		mapPut("name" + i, " ")
mapPut("score" + i, 0) next i endif

;スコア直し
done = false
for i = 1 to highscoreListMax
	if done = false then
		if score > mapGet("score" + i) then ;今回のスコアがもっと大きいなら
			j = highscoreListMax
			while j > i ;これ以下のスコアを一個下にずらす
				mapPut("name" + j, mapGet("name" + (j-1)))
				mapPut("score" + j, mapGet("score" + (j-1)))
			j = j-1 wend
			mapPut("score" + i, score) ;今回のスコアを挿入
			print "ハイスコア！　名前を入力してください："
			mapPut("name" + i, read())
		done = true endif
	endif
next i

;書き込み
if openFileWrite(highscoreFile) = true then
	for i = 1 to highscoreListMax
		name = mapGet("name" + i)
		highscore = mapGet("score" + i)
		writeFile(name)
	writeFile(highscore) next i
closeFileWrite() endif

;スコア表示
for i = 1 to highscoreListMax
	name = mapGet("name" + i)
	highscore = mapGet("score" + i)
print toStr(i) + "位:\t" + name + "\t" + toStr(highscore) + "点" next i


END