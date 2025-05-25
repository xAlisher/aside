module.exports = {
  source: ['ui/theme/design-tokens.json'],
  platforms: {
    android: {
      transformGroup: 'android',
      buildPath: 'build/theme/',
      files: [
        {
          destination: 'colors.xml',
          format: 'android/colors',
          filter: {
            attributes: { category: 'color' }
          }
        }
      ]
    }
  }
};
