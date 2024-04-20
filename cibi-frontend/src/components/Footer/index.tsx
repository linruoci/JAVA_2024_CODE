import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';
const Footer: React.FC = () => {
  const defaultMessage = '一个还在努力的程序员';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: '词智能 BI',
          title: '词智能 BI',
          href: '#',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/linruoci',
          blankTarget: true,
        },
        {
          key: '词智能 BI',
          title: '词智能 BI',
          href: '#',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
