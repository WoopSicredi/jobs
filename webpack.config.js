var HtmlWebpackPlugin = require('html-webpack-plugin');
var path = require('path');

const config = (env) => {
    let isProduction = false;
    
    if (env) {
        if (env.prod) {
            isProduction = true;
        }
    }

    return {
        mode: isProduction? "production" : "development",
        entry: "./src/main.tsx",
        resolve: {
            extensions: [ '.tsx', '.ts', '.js' ],
        },
        module: {
            rules: [
                {
                    test: /\.tsx?$/,
                    use: 'ts-loader',
                    exclude: /node_modules/,
                },
                {
                    test: /\.css$/i,
                    use: [
                        'style-loader',
                        {
                            loader: 'css-loader',
                            options: {
                                modules: true
                            },
                        }
                    ]
                }
            ],
        },
        plugins: [new HtmlWebpackPlugin({
            template: "./assets/index.html"
        })]
    }
};

module.exports = [config];