/**
 * Created by daminovn on 18.04.2017.
 */
var variable = function (a) {
    return function (x) {
        return x;
    }
};
var cnst = function (a) {
    return function (x) {
        return a;
    }
};
var take = function (part, a, x) {
    if (typeof a === "function") {
        return a(x);
    } else {
        return a;
    }
};
var add = function (a, b) {
    return function (x) {
        var first = take(first, a, x);
        var second = take(second, b, x);
        return first + second;
    }
};
var subtract = function (a, b) {
    return function (x) {
        var first = take(first, a, x);
        var second = take(second, b, x);
        return first - second;
    }
};
var multiply = function (a, b) {
    return function (x) {
        var first = take(first, a, x);
        var second = take(second, b, x);
        return first * second;
    }
};
var divide = function (a, b) {
    return function (x) {
        var first = take(first, a, x);
        var second = take(second, b, x);
        return first / second;
    }
};
var expr = subtract(
    multiply(
        cnst(2),
        variable("x")
    ),
    cnst(3)
);
var isDigit = function (a) {
    if (a >= '0' && a <= '9') {
        return true;
    } else {
        return false;
    }
};
var isOperation = function (a) {
    var op = ['+', '-', '*', '/'];
    for (var i = 0; i < 4; i++) {
        if (a === op[i]) {
            return true;
        }
    }
    return false;
};
var getOperation = function (a, second, first) {
    var op = ['+', '-', '*', '/'];
    var func = [add, subtract, multiply, divide];
    for (var i = 0; i < 4; i++) {
        if (a === op[i]) {
            return func[i](first, second);
        }
    }
}
var parse = function (expression) {
    return function (x) {
        var stack = new Array();
        for (var i = 0; i < expression.length; i++) {
            if (isDigit(expression[i])) {
                var j = i;
                console.log("Digit");
                while (j < expression.length && isDigit(expression[j])) {
                    j++;
                }
                var res = parseInt(expression.substr(i, j));
                stack.push(cnst(res));
                i = j - 1;
            } else if (isOperation(expression[i])) {
                console.log("Operation");
                var res = getOperation(expression[i], stack.pop(), stack.pop());
                stack.push(res);
            } else if (expression[i] >= 'a' && expression[i] <= 'z'){
                console.log("Variable");
                stack.push(variable(expression[i]));
            }
        }
        return stack.pop()(x);
    }
};
