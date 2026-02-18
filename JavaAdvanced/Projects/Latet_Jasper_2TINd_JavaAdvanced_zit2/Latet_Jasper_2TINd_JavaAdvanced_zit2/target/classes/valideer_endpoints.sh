BASE="${BASE_URL:-http://localhost:8080}"
JSON_CT="Content-Type: application/json"

echo -e "==> Validate Meals API endpoints at: $BASE\n"

echo "1) Create meal MEAL_01"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X POST -H "$JSON_CT" \
  -d '{"code":"MEAL_01","name":"Spaghetti Carbonara","origin":"CENTER","destination":"EAST"}' \
  "$BASE/meals"

echo "1b) Create meal MEAL_01 again (expect 409)"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X POST -H "$JSON_CT" \
  -d '{"code":"MEAL_01","name":"Spaghetti Carbonara","origin":"CENTER","destination":"EAST"}' \
  "$BASE/meals"

echo "2) Create meal MEAL_02 (to add to delivery)"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X POST -H "$JSON_CT" \
  -d '{"code":"MEAL_02","name":"Margherita Pizza","origin":"CENTER","destination":"NORTH"}' \
  "$BASE/meals"

echo "3) Add MEAL_02 to delivery 1"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X PUT "$BASE/deliveries/1/meals/MEAL_02"

echo "3b) Add MEAL_02 again to delivery 1 (expect 400)"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X PUT "$BASE/deliveries/1/meals/MEAL_02"

echo "4) Deliver MEAL_02"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X PUT "$BASE/meals/MEAL_02/deliver"

echo "4b) Deliver MEAL_02 again (expect 400)"
curl -s -o /dev/null -w "HTTP %{http_code}\n" -X PUT "$BASE/meals/MEAL_02/deliver"

echo "5) Statistics"
if command -v jq >/dev/null 2>&1; then
  curl -s -X GET "$BASE/meals/statistics" | jq .
else
  curl -s -X GET "$BASE/meals/statistics"
fi

echo -e "\nDone."
