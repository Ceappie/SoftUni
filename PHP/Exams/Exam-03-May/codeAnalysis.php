<?php
$code = $_GET['code'];
//var_dump($code);
$data = ['variables' => [], 'loops' => ['while' => [], 'for' => [], 'foreach' => []], 'conditionals' => []];
$varRegex = '/(\$.+?)[\W]/';
preg_match_all($varRegex, $code, $vars);
//var_dump($vars);
foreach ($vars[1] as $variable) {
    $data['variables'][$variable]++;
}

$whileRegex = '/(while\s*\([\w\W]*?\))\s*{/';
preg_match_all($whileRegex, $code, $whiles);
//var_dump($whiles);
foreach ($whiles[1] as $while) {
    $data['loops']['while'][] = $while;
}

$forRegex = '/(for\s*\([\s\S]*?\))\s*{/';
preg_match_all($forRegex, $code, $fors);
foreach ($fors[1] as $for) {
    $data['loops']['for'][] = $for;
}

$foreachRegex = '/(foreach\s*\([\w\W]*?\))\s*{/';
preg_match_all($foreachRegex, $code, $each);
foreach ($each[1] as $ea) {
    $data['loops']['foreach'][] = $ea;
}
//var_dump($data);

$ifRegex = '/((?:else\s+if|if)\s*\([\w\W]*?\))\s*{/';
preg_match_all($ifRegex, $code, $if);
foreach ($if[1] as $else) {
    $data['conditionals'][] = $else;
}
echo json_encode($data);
