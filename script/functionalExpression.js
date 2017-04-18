/**
 * Created by daminovn on 18.04.2017.
 */
var variable = function (a) {
    return function (x, y, z) {
        if (a === 'x') {
            return x;
        } else if (a === 'y') {
            return y;
        } else {
            return z;
        }
    }
};
var cnst = function (a) {
    return function (x, y, z) {
        return a;
    }
};
var take = function (part, a, x, y, z) {
    if (typeof a === "function") {
        return a(x, y, z);
    } else {
        return a;
    }
};
var negate = function (a) {
    return function (x, y, z) {
        var first = take(first, a, x, y, z);
        return -first;
    }
};
var add = function (a, b) {
    return function (x, y, z) {
        var first = take(first, a, x, y, z);
        var second = take(second, b, x, y, z);
        return first + second;
    }
};
var subtract = function (a, b) {
    return function (x, y, z) {
        var first = take(first, a, x, y, z);
        var second = take(second, b, x, y, z);
        return first - second;
    }
};
var multiply = function (a, b) {
    return function (x, y, z) {
        var first = take(first, a, x, y, z);
        var second = take(second, b, x, y, z);
        return first * second;
    }
};
var divide = function (a, b) {
    return function (x, y, z) {
        var first = take(first, a, x, y, z);
        var second = take(second, b, x, y, z);
        return first / second;
    }
};
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
};
var parse = function (expression) {
    return function (x, y, z) {
        var stack = new Array();
        for (var i = 0; i < expression.length; i++) {
            if (isDigit(expression[i]) || (i + 1 < expression.length && expression[i] === '-' && isDigit(expression[i + 1]))) {
                var j = i;
                while (j < expression.length && (expression[j] === '-' || isDigit(expression[j]))) {
                    j++;
                }
                var res = parseInt(expression.substr(i, j - i));
                stack.push(cnst(res));
                i = j - 1;
            } else if (isOperation(expression[i])) {
                var res = getOperation(expression[i], stack.pop(), stack.pop());
                stack.push(res);
            } else if (i + 6 <= expression.length && expression.substr(i, 6) === 'negate') {
                var res = stack.pop();
                stack.push(negate(res));
                i += 5;
            } else if (expression[i] >= 'a' && expression[i] <= 'z'){
                stack.push(variable(expression[i]));
            }
        }
        return stack.pop()(x, y, z);
    }
};
